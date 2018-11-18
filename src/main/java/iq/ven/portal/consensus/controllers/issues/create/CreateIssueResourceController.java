package iq.ven.portal.consensus.controllers.issues.create;

import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.create.payload.CreateIssueRequest;
import iq.ven.portal.consensus.database.issue.model.Issue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/issue")
public class CreateIssueResourceController extends AbstractController {


    @RequestMapping(path = "/create-issue-request", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAssigneeOnIssue(HttpServletRequest httpServletRequest,
                                                     HttpServletResponse httpServletResponse,
                                                     @Valid CreateIssueRequest createIssueRequest) {
        Map<String, Object> result = new HashMap<>();

        Issue issue = new Issue();


        return result;
    }

}
