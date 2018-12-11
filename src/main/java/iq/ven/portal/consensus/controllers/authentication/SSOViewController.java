package iq.ven.portal.consensus.controllers.authentication;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.model.base.UserData;
import iq.ven.portal.consensus.common.util.helpers.BoardsHelper;
import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.HistoryEntry;
import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.board.model.main.BoardColumn;
import iq.ven.portal.consensus.database.board.model.main.BoardType;
import iq.ven.portal.consensus.database.issue.model.*;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.services.data.IssueDataService;
import iq.ven.portal.consensus.services.data.ProjectDataService;
import iq.ven.portal.consensus.services.data.RolesDataService;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sso")
public class SSOViewController extends AbstractController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private RolesDataService rolesDataService;

    @Autowired
    private ProjectUser projectUser;

    @Autowired
    private UserState userState;

    @Autowired
    private IssueDataService issueDataService;

    @Autowired
    private ProjectDataService projectDataService;

    private static final Logger logger = LoggerFactory.getLogger(SSOViewController.class);

    @RequestMapping(value = {"/logindummy"}, method = RequestMethod.GET)
    public ModelAndView loginToDummy(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        User checker = userDataService.findUserByEmail("user@com.com");
        if (checker == null) {
            User userr = createUser();
            setUserParametersAfterLogin(userr);

            HttpSession session = request.getSession();
            session.setAttribute("user_full_name", userr.getFullName());
            session.setAttribute("user_email", userr.getEmail());
            session.setAttribute("user_is_active", userr.isActive());

        }

        checker = userDataService.findUserByEmail("user@com.com");
        Project project = createProject(checker);

        Board board = createBoard(project, checker, null);

        Issue issue = createIssue(project, checker, board);

        modelAndView.setViewName("redirect:/issues/issue");
        return modelAndView;
    }


    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Login page");


//--------------------------------START OF TEST-------------------------------------
//
   //     User checker = userDataService.findUserByEmail("user@com.com");
   //     if (checker == null) {
   //         User userr = createUser();
   //         Project project = createProject(userr);
   //         Issue issue = createIssue(project, userr);
   //     }
//
   //     User genUser = null;
   //     for (int i = 0; i < 10; i++) {
   //         genUser = generateAndSaveUser();
   //     }
   //     Project genProj = null;
   //     for (int i = 0; i < 10; i++) {
   //         genProj = createProject(checker);
   //     }
   //     Issue genIssue = null;
   //     for (int i = 0; i < 10; i++) {
   //         genIssue = createIssue(genProj, genUser);
   //     }
   //     Board genBoard = null;
   //     for (int i = 0; i < 1; i++) {
   //         genBoard = createBoard(genProj, genUser, null);
   //     }

        //   List<Issue> foundIssue = issueDataService.findIssueByNameIgnoreCaseContaining("Issue ly");
        //   System.out.println(foundIssue);
//--------------------------------EMD OF TEST-------------------------------------

        modelAndView.setViewName("auth/login");
        //modelAndView.setViewName("redirect:sso/login");
        return modelAndView;
    }


    private User generateAndSaveUser() {
        User userExists = null;
        User user = null;
        String generatedEmail = generateString(randomNumber(5, 14)) + "@com.com";
        userExists = userDataService.findUserByEmail(generatedEmail);
        if (userExists == null) {
            user = new User();
            user.setUsername(generateString(randomNumber(5, 14)));
            user.setFirstName(generateString(randomNumber(5, 14)));
            user.setLastName(generateString(randomNumber(5, 14)));
            user.setEmail(generatedEmail);
            user.setPassword("user");

            userState.setLogInDate(new Date());
            try {
                user = userDataService.saveUser(user);
                user = userDataService.findUserByEmail(generatedEmail);
            } catch (Exception e) {
                logger.error("Error in saving user", e);
            }

            //    UserData userData = UserData.UserDataBuilder.anUserData()
            //            .withEmail(user.getEmail())
            //            .withFirstName(user.getFirstName())
            //            .withLastName(user.getLastName())
            //            .withUsername(user.getUsername())
            //            .build();
            //    projectUser.setUserData(userData);
            //    userState.setUserRole(user.getRoles());

            logger.info("USERGEN::::::::::::::" + user.getEmail() + "   " + user.getFullName() + "    usernaem::" + user.getUsername() + "  :::::::::::");
            System.out.println("USERGEN::::::::::::::" + user.getEmail() + "   " + user.getFullName() + "    usernaem::" + user.getUsername() + "  :::::::::::");
            return user;
        } else {
            return userExists;
        }
    }

    private static String generateString(int lengthOfStr) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(lengthOfStr);
        for (int i = 0; i < lengthOfStr; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    private static int randomNumber(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private User createUser() {
        User userExists = null;
        User user = null;

        userExists = userDataService.findUserByEmail("user@com.com");
        if (userExists == null) {
            user = new User();
            user.setUsername("user");
            user.setFirstName("Firname");
            user.setLastName("Laname");
            user.setEmail("user@com.com");
            user.setPassword("user");

            userState.setLogInDate(new Date());
            try {
                user = userDataService.saveUser(user);
            } catch (Exception e) {
                logger.error("Error in saving user", e);
            }

            UserData userData = UserData.UserDataBuilder.anUserData()
                    .withEmail(user.getEmail())
                    .withFirstName(user.getFirstName())
                    .withLastName(user.getLastName())
                    .withUsername(user.getUsername())
                    .withUserId(user.getId())
                    .build();
            projectUser.setUserData(userData);
            userState.setUserRole(user.getRoles());


            return user;
        } else {
            return userExists;
        }
    }

    Project createProject(User user) {
        Project project = new Project();

        String randNum = randomNumber(0, 1000000) + " " + randomNumber(0, 1000000);

        project.setName("Test project" + randNum);
        project.setAbbreviation("PROJ" + randomNumber(0, 1000000));
        project.setIssues(new ArrayList<Issue>());
        project.setBoards(new ArrayList<Board>());

        if (user != null)
            project.setManager(userDataService.findUserByEmail(user.getEmail()));
        project.setDescription(generateDescription());

        project = projectDataService.saveProject(project);
        String projABr = project.getAbbreviation();
        project = projectDataService.findProjectByAbbreviation(projABr);
        return project;
    }

    Issue createIssue(Project project, User user, Board board) {
        Issue issue = new Issue();
        issue.setName("Issue" + generateString(10));
        issue.setAssignee(user);
        issue.setDueDate(new Date());
        issue.setPriority(IssuePriorities.CRITICAL);
        issue.setProject(project);
        issue.setParentIssue(null);
        issue.setReporters(Arrays.asList(user));
        issue.setStatus(IssueStatuses.OPEN);
        issue.setType(IssueTypes.BUG);


        issue.setDescription(generateDescription());


        issue.setAttachments(new ArrayList<IssueAttachment>());
        issue.setComments(new ArrayList<IssueComment>());
        issue.setHistory(new ArrayList<HistoryEntry>());


        issue.setBoard(board);
        issue.setBoardColumn(board.getColumns().get(0));


        issue = issueDataService.saveIssue(issue);
        issue = issueDataService.findIssueByNameIgnoreCaseContaining(issue.getName()).get(0);
        return issue;
    }


    Board createBoard(Project project, User user, Issue issue) {
        String randNum = randomNumber(0, 1000000) + " " + randomNumber(0, 1000000);

        Board board = new Board();
        board.setName("Boar control " + randNum);
        board.setBoardType(BoardType.KANBAN);
        board.setDescription(generateDescription());

        List<BoardColumn> standardColumns = BoardsHelper.generateStandardColumnsForBoard();
        board.setColumns(standardColumns);
        if (issue != null)
            board.getColumns().get(0).setIssues(Arrays.asList(issue));

        board.setProject(project);
        board.setManagers(Arrays.asList(user));

        Board savedBoard = boardsDataService.save(board);

        savedBoard = boardsDataService.findBoardByName(board.getName());
        return savedBoard;
    }


    String generateDescription() {
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            desc.append(generateString(20)).append(" ");
        }
        String descGen = "desc 1324 " + desc.toString();
        return descGen;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("auth/registration");

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        clearUserParametersAfterLogin();
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    private void clearUserParametersAfterLogin() {
        userState.clear();
        projectUser.clear();
    }

    private void setUserParametersAfterLogin(User userDetails) {
        UserData userData = UserData.UserDataBuilder.anUserData()
                .withEmail(userDetails.getEmail())
                .withFirstName(userDetails.getFirstName())
                .withLastName(userDetails.getLastName())
                .withUsername(userDetails.getUsername())
                .withUserId(userDetails.getId())
                .build();
        projectUser.setUserData(userData);
        userState.setUserRole(userDetails.getRoles());
        userState.setLogInDate(new Date());
    }


}
