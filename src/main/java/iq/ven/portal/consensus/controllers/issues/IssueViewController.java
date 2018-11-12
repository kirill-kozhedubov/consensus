package iq.ven.portal.consensus.controllers.issues;

import iq.ven.portal.consensus.common.beans.ProjectUser;
import iq.ven.portal.consensus.common.beans.UserState;
import iq.ven.portal.consensus.common.util.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/issues")
public class IssueViewController extends AbstractController{

    @Autowired
    private ProjectUser projectUser;

    @Autowired
    private UserState userState;


    @RequestMapping(value = {"/{projectKey}-{issueId}"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              @PathVariable String projectKey, @PathVariable long issueId) {
        ModelAndView modelAndView = new ModelAndView();



        //find issue in db
        String issueKey = "[" + projectKey + "-" + issueId + "] ";
        String issueName = "";


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, issueKey + issueName);
        modelAndView.setViewName("issues/issue");
        return modelAndView;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView issue() {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Issue");
        modelAndView.setViewName("issues/issue");
        return modelAndView;
    }

    //TODO REMOVE BECAUSE IT FOR TEST
    @RequestMapping(value = {"/issue"}, method = RequestMethod.GET)
    public ModelAndView issuegetlul() {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "Issue");
        modelAndView.setViewName("issues/issue");
        return modelAndView;
    }


}
