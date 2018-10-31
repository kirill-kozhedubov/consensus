package iq.ven.portal.consensus.controllers.authentication;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.model.base.UserData;
import iq.ven.portal.consensus.common.validators.UserValidator;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.authentication.payload.UserForRegistration;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.services.data.RolesDataService;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sso")
public class SSOResourceController extends AbstractController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private RolesDataService rolesDataService;

    @Autowired
    private ProjectUser projectUser;

    @Autowired
    private UserState userState;

    @RequestMapping(path = "/login/request", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginRequest(HttpServletResponse response, HttpServletRequest request,
                                            @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        Map<String, Object> result = new HashMap<>();
        User userDetails = userDataService.findUserByEmail(email);
        if (userDetails != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (userDetails.getUsername().equals(email) && bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                setUserParametersAfterLogin(userDetails);
                request.getSession(true);

                result.put("success", true);
            } else {
                result.put("success", false);
            }
        } else {
            result.put("isError", true);
            result.put("errorMessage", "Email or password is incorrect");
        }
        return result;
    }


    @RequestMapping(value = "/registration/request", method = RequestMethod.POST)
    public Map<String, Object> createNewUser(@Valid UserForRegistration userData, BindingResult bindingResult,
                                             HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        User userExists = null;
        User user = null;
        if (userData != null) {
            userExists = userDataService.findUserByEmail(userData.getEmail());
        }
        if (userExists != null) {
            result.put("errorMessage", "There is error in your registration try again");
        } else {
            if (UserValidator.validateUserDataForRegistration(userData)) {
                user = new User();
                user.setUsername(userData.getUsername());
                user.setFirstName(userData.getFirstName());
                user.setLastName(userData.getLastName());
                user.setEmail(userData.getEmail());
                user.setPassword(userData.getPassword());
            }

        }
        if (bindingResult.hasErrors()) {
            result.put("errorMessage", "There is error in your registration, try again");
        } else {
            if (user != null) {
                try {
                    userDataService.saveUser(user);
                    result.put("redirectURL", "/sso/login");
                    result.put("successfulRegistrationMessage", "User has been registered successfully");
                } catch (Exception e) {
                    logger.error("Error in saving user", e);
                    result.put("userData", user);
                    result.put("failedRegistrationMessage", "User has not been registered");
                }
            }
        }
        return result;
    }

    private void setUserParametersAfterLogin(User userDetails) {
        UserData userData = UserData.UserDataBuilder.anUserData()
                .withEmail(userDetails.getEmail())
                .withFirstName(userDetails.getFirstName())
                .withLastName(userDetails.getLastName())
                .withUsername(userDetails.getUsername())
                .build();
        projectUser.setUserData(userData);
        userState.setUserRole(userDetails.getRoles());
        userState.setLogInDate(new Date());
    }


}
