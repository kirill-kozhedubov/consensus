package iq.ven.portal.consensus.controllers.kanbanboard.create;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.create.payload.ProjectAvailableForIssue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/boards")
public class BoardCreateViewController extends AbstractController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              @RequestParam(required = false, value = "projectId") String projectId) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProjectAvailableForIssue> projects = projectDataService.getProjectsAvailableForIssue();
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("projectId", TemplatesHelper.transformStringToLong(projectId));

        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Create new Board");
        modelAndView.setViewName("kanban-board/kanban-board-create");
        return modelAndView;
    }

}
