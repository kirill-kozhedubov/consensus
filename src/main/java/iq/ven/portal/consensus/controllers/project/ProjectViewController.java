package iq.ven.portal.consensus.controllers.project;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.project.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/projects")
public class ProjectViewController extends AbstractController {

    @RequestMapping(value = {"/project/{projectAbbr}"}, method = RequestMethod.GET)
    public ModelAndView viewProject(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable String projectAbbr) {
        ModelAndView modelAndView = new ModelAndView("projects/project");


        Project projectFromDB = projectDataService.findProjectByAbbreviation(projectAbbr);


        String projectName = projectFromDB.getName();
        String projectAbbreviation = projectFromDB.getAbbreviation();
        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "[" + projectAbbreviation + "] " + projectName);
        return modelAndView;
    }


}
