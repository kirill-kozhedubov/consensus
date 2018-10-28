package iq.ven.portal.consensus.controllers.dashboard;

import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class DashboardViewController extends AbstractController {

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        if (projectUser.getUserData() != null) {
            modelAndView.setViewName("redirect:/dashboard");
        } else {
            modelAndView.setViewName("redirect:sso/login");
        }







        return modelAndView;
    }


}