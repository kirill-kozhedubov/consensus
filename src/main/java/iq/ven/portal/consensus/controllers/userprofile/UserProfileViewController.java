package iq.ven.portal.consensus.controllers.userprofile;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.common.viewconvertors.UserViewConverter;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/users")
public class UserProfileViewController extends AbstractController {

    @RequestMapping(value = {"/user/{username}"}, method = RequestMethod.GET)
    public ModelAndView viewProject(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable String username) {
        ModelAndView modelAndView = new ModelAndView("user/profile");

        User user = userDataService.findUserByUsername(username);

        modelAndView.addObject("user", UserViewConverter.convertUser(user, false));

        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, username + "'s Profile");
        return modelAndView;
    }


}
