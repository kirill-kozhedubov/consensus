package iq.ven.portal.consensus.controllers.issues.create;

import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.create.payload.CreateIssueRequest;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.model.IssuePriorities;
import iq.ven.portal.consensus.database.issue.model.IssueStatuses;
import iq.ven.portal.consensus.database.issue.model.IssueTypes;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/issues")
public class CreateIssueResourceController extends AbstractController {


    @RequestMapping(path = "/create-issue-request", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAssigneeOnIssue(HttpServletRequest httpServletRequest,
                                                     HttpServletResponse httpServletResponse,
                                                     @Valid CreateIssueRequest createIssueRequest) {
        Map<String, Object> result = new HashMap<>();

        Issue issue = new Issue();


        int startUsername = createIssueRequest.getAssignee().indexOf('(');
        int endUsername = createIssueRequest.getAssignee().indexOf(')');
        String assigneeUsername = createIssueRequest.getAssignee().substring(startUsername, endUsername);
        User assignee = userDataService.findUserByUsername(assigneeUsername);

        issue.setAssignee(assignee);
        issue.setReporters(Arrays.asList(assignee)); //TODO add manager of project
        issue.setName(createIssueRequest.getName());
        issue.setPriority(IssuePriorities.getIssuePriorityByName(createIssueRequest.getPriority()));
        issue.setType(IssueTypes.getIssueTypeByName(createIssueRequest.getType()));
        issue.setStatus(IssueStatuses.OPEN);
        issue.setDescription(createIssueRequest.getDescription());


        issue.setAttachments(Collections.emptyList()/* TODO createIssueRequest.getAttachments()*/);
        issue.setComments(Collections.emptyList());

        Project project = projectDataService.findProjectById(Long.parseLong(createIssueRequest.getProject()));
        issue.setProject(project);

        if (!StringUtils.isEmpty(createIssueRequest.getParentIssue())) {
            Issue parentIssue = issueDataService.findIssueByIssueKey(createIssueRequest.getParentIssue());

            if (parentIssue != null) {
                issue.setParentIssue(parentIssue);
            }
        }


        return result;
    }

}
