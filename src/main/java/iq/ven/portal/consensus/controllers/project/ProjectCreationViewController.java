package iq.ven.portal.consensus.controllers.project;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/projects")
public class ProjectCreationViewController extends AbstractController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createProjectRenderForm(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView("projects/project-create");
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Project create");


        return modelAndView;
    }


}
