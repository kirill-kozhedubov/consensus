package iq.ven.portal.consensus.controllers.dashboard;

import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DashboardViewController extends AbstractController {

    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/user/dashboard");
        return modelAndView;
    }


}