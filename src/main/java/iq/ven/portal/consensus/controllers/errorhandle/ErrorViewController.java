package iq.ven.portal.consensus.controllers.errorhandle;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error")
public class ErrorViewController {

    @RequestMapping(value = {"/not-found"}, method = RequestMethod.GET)
    public ModelAndView pageNotFoundError() {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Page not found");
        modelAndView.setViewName("errorhandle/page-not-found");
        return modelAndView;
    }

}
