package iq.ven.portal.consensus.controllers.issues;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.common.viewconvertors.IssueViewConverter;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.issue.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/issues")
public class IssueViewController extends AbstractController {

    @Autowired
    private ProjectUser projectUser;

    @Autowired
    private UserState userState;


    @RequestMapping(value = {"/issue/{projectKey}-{issueId}"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              @PathVariable String projectKey, @PathVariable Long issueId) {
        ModelAndView modelAndView = new ModelAndView();

        Issue issue = issueDataService.findIssueById(issueId);
        if (issue != null) {
            //find issue in db
            String issueKey = "[" + projectKey + "-" + issueId + "] ";
            String issueName = "";


            modelAndView.addObject(TemplatesHelper.PAGE_TITLE, issueKey + issueName);
            modelAndView.setViewName("issues/issue");
            return modelAndView;

        } else {
            return redirectToPageNotFound();
        }

    }


    //TODO REMOVE IT // dont know what to do with it
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView issue() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:dashboard");
        return modelAndView;
    }

    //TODO REMOVE BECAUSE IT FOR TEST
    @RequestMapping(value = {"/issue"}, method = RequestMethod.GET)
    public ModelAndView issuegetlul() {
        ModelAndView modelAndView = new ModelAndView();


        List<Issue> issues = issueDataService.findIssueByNameIgnoreCaseContaining("Issue");
        Issue issue = issues.get(0);
        Map<String, Object> issueMap = IssueViewConverter.convertIssue(issue, false);
        modelAndView.addObject("issue", issueMap);


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Issue");
        modelAndView.setViewName("issues/issue");
        return modelAndView;
    }


}
