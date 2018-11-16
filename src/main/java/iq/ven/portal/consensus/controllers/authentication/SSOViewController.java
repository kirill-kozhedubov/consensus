package iq.ven.portal.consensus.controllers.authentication;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.model.base.UserData;
import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.model.IssuePriorities;
import iq.ven.portal.consensus.database.issue.model.IssueStatuses;
import iq.ven.portal.consensus.database.issue.model.IssueTypes;
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
public class SSOViewController {

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

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Login page");


//--------------------------------START OF TEST-------------------------------------

        User checker = userDataService.findUserByEmail("user@com.com");
        if (checker == null) {
            User userr = createUser();
            Project project = createProject(userr);
            Issue issue = createIssue(project, userr);
        }
        for (int i = 0; i < 50; i++) {
            generateAndSaveUser();
        }
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
        String generatedEmail = generateString(randomNumber(5,14)) + "@com.com";
        userExists = userDataService.findUserByEmail(generatedEmail);
        if (userExists == null) {
            user = new User();
            user.setUsername(generateString(randomNumber(5,14)));
            user.setFirstName(generateString(randomNumber(5,14)));
            user.setLastName(generateString(randomNumber(5,14)));
            user.setEmail(generatedEmail);
            user.setPassword("user");

            userState.setLogInDate(new Date());
            try {
                user = userDataService.saveUser(user);
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

           logger.info("USERGEN::::::::::::::" + user.getEmail() + "   " + user.getFullName() + "    usernaem::" +  user.getUsername() + "  :::::::::::");
            System.out.println("USERGEN::::::::::::::" + user.getEmail() + "   " + user.getFullName() + "    usernaem::" +  user.getUsername() + "  :::::::::::");
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
            user.setFirstName("Firstname");
            user.setLastName("Lastname");
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
        project.setName("Test project");
        project.setAbbreviation("PROJ");
        project.setIssues(Collections.emptyList());
        project.setBoards(Collections.emptyList());


        project.setManager(userDataService.findUserByEmail(user.getEmail()));
        project.setDescription("lyl descrtiption");

        project = projectDataService.saveProject(project);

        return project;
    }

    Issue createIssue(Project project, User user) {
        Issue issue = new Issue();
        issue.setName("Issue ly123");
        issue.setAssignee(userDataService.findUserByEmail(user.getEmail()));
        issue.setDueDate(new Date());
        issue.setPriority(IssuePriorities.CRITICAL);
        issue.setProject(projectDataService.findProjectByAbbreviation(project.getAbbreviation()));
        issue.setParentIssue(null);
        issue.setReporters(Arrays.asList(userDataService.findUserByEmail(user.getEmail())));
        issue.setStatus(IssueStatuses.CLOSED);
        issue.setType(IssueTypes.BUG);
        issue.setDescription("desc 1324");
        issue.setAttachments(Collections.emptyList());
        issue.setComments(Collections.emptyList());
        issue.setHistory(Collections.emptyList());

        issue = issueDataService.saveIssue(issue);

        return issue;
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

}
