package iq.ven.portal.consensus.controllers.issues;

import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.issues.create.payload.FindUsersResponse;
import iq.ven.portal.consensus.controllers.issues.create.payload.FoundUser;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.services.data.IssueDataService;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/issues")
public class IssueResourceController extends AbstractController {


    @Autowired
    private UserDataService userDataService;

    @Autowired
    private IssueDataService issueDataService;


    @RequestMapping(path = "/update-assignee-on-issue", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateAssigneeOnIssue(@RequestParam(value = "username") String username,
                                                     @RequestParam(value = "fullName") String fullName,
                                                     @RequestParam(value = "issueKey") String issueKey) {
        Map<String, Object> result = new HashMap<>();

        User user = userDataService.findUserByUsername(username);
        Issue issue = issueDataService.findIssueByIssueKey(issueKey);

        Issue updatedIssue = issueDataService.updateIssueAssignee(issue, user, projectUser.getUserData().getUserId());

        result.put("fullName", user.getFullName());
        result.put("username", user.getUsername());

        return result;
    }

    @RequestMapping(path = "/find-user", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findUser(@RequestParam(value = "searchInput") String searchInput) {
        Map<String, Object> result = new HashMap<>();

        FindUsersResponse findUsersResponse = null;
        if (searchInput.length() > 2) {
            findUsersResponse = findUsers(searchInput);
            if (findUsersResponse.getFoundUsers().size() > 0) {
                result.put("data", findUsersResponse);
            }
        }

        return result;
    }


    private FindUsersResponse findUsers(String searchInput) {
        List<User> users = userDataService.findUsersForAssigneeChange(searchInput);
        List<FoundUser> foundUsers = new ArrayList<>();
        for (User user : users) {
            FoundUser foundUser = new FoundUser(user.getFullName(), user.getUsername(), user.getAvatar());
            foundUsers.add(foundUser);
        }
        FindUsersResponse findUsersResponse = new FindUsersResponse(foundUsers);
        return findUsersResponse;
    }
}
