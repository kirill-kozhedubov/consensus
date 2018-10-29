package iq.ven.portal.consensus.controllers.project;

import iq.ven.portal.consensus.common.util.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/projects")
public class ProjectCreationViewController extends AbstractController{

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("auth/login");
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Project create");


        return modelAndView;
    }


    }
