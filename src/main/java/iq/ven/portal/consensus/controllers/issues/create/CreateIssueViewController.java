package iq.ven.portal.consensus.controllers.issues.create;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.issue.model.IssuePriorities;
import iq.ven.portal.consensus.database.issue.model.IssueStatuses;
import iq.ven.portal.consensus.database.issue.model.IssueTypes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/issue")
public class CreateIssueViewController extends AbstractController {

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();



        modelAndView.addObject("priorities", IssuePriorities.values());
        modelAndView.addObject("types", IssueTypes.values());
        modelAndView.addObject("statuses", IssueStatuses.values());


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Create new Issue");
        modelAndView.setViewName("issues/issue-create");
        return modelAndView;
    }


}
