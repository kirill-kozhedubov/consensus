package iq.ven.portal.consensus.controllers.authentication;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.model.base.UserData;
import iq.ven.portal.consensus.common.util.TemplatesHelper;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/sso")
public class SSOViewController {

    @Autowired
    private UserDataService userService;

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
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE,"Login page");


//--------------------------------START OF TEST-------------------------------------

        User userr = createUser();
        Project project = createProject(userr);
        Issue issue = createIssue(project, userr);

//--------------------------------EMD OF TEST-------------------------------------


        return modelAndView;
    }


    private User createUser() {
        User userExists = null;
        User user = null;

        userExists = userService.findUserByEmail("user@com.com");
        if (userExists == null) {
            user = new User();
            user.setUsername("user");
            user.setFirstName("Firstname");
            user.setLastName("Lastname");
            user.setEmail("user@com.com");
            user.setPassword("user");

            UserData userData = UserData.UserDataBuilder.anUserData()
                    .withEmail(user.getEmail())
                    .withFirstName(user.getFirstName())
                    .withLastName(user.getLastName())
                    .withUsername(user.getUsername())
                    .build();
            projectUser.setUserData(userData);
            userState.setUserRole(user.getRoles());
            userState.setLogInDate(new Date());
            try {
                userService.saveUser(user);
            } catch (Exception e) {
                logger.error("Error in saving user", e);
            }
            //   modelAndView.setViewName("redirect:sso/login");
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
        project.setManager(user);
        project.setDescription("lyl descrtiption");

        project = projectDataService.saveProject(project);

        return project;
    }

    Issue createIssue(Project project, User user) {
        Issue issue = new Issue();
        issue.setName("Issue ly123");
        issue.setAssignee(user);
        issue.setDueDate(new Date());
        issue.setPriority(IssuePriorities.CRITICAL);
        issue.setProject(project);
        issue.setParentIssue(null);
        issue.setReporters(Arrays.asList(user));
        issue.setStatus(IssueStatuses.CLOSED);
        issue.setType(IssueTypes.BUG);
        issue.setDescription("desc 1324");
        issue.setAttachments(Collections.EMPTY_LIST);
        issue.setComments();
        issue.setHistory();



        return null;
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
