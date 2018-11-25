package iq.ven.portal.consensus.controllers.issues.create;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.create.payload.ProjectAvailableForIssue;
import iq.ven.portal.consensus.database.issue.model.IssuePriorities;
import iq.ven.portal.consensus.database.issue.model.IssueStatuses;
import iq.ven.portal.consensus.database.issue.model.IssueTypes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/issue")
public class CreateIssueViewController extends AbstractController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              @RequestParam(required = false, value = "boardId") String boardId,
                              @RequestParam(required = false, value = "projectId") String projectId) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProjectAvailableForIssue> projects = projectDataService.getProjectsAvailableForIssue();
        modelAndView.addObject("projects", projects);

        if (projectId.matches("[0-9]+") && projectId.length() > 0) {
            modelAndView.addObject("projectId", Long.parseLong(projectId));
        }

        modelAndView.addObject("priorities", IssuePriorities.getIssuePrioritiesStringsList());
        modelAndView.addObject("types", IssueTypes.getIssueTypesStringsList());
        modelAndView.addObject("statuses", IssueStatuses.getIssueStatusesStringsList());


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Create new Issue");
        modelAndView.setViewName("issues/issue-create");
        return modelAndView;
    }


}
