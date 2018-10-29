package iq.ven.portal.consensus.controllers.authentication;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.model.base.UserData;
import iq.ven.portal.consensus.common.util.TemplatesHelper;
import iq.ven.portal.consensus.database.user.model.User;
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

    private static final Logger logger = LoggerFactory.getLogger(SSOViewController.class);

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE,"Login page");




//--------------------------------START OF TEST-------------------------------------

//--------------------------------EMD OF TEST-------------------------------------


        return modelAndView;
    }


    private void createUser() {
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
        }
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
