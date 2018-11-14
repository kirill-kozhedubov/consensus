package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.List;

public interface IssueDataService {

    Issue saveIssue(Issue issue);

    Issue findIssueById(long id);

    Issue findIssueByIssueKey(String issueKey);

    List<Issue> findIssueByNameIgnoreCaseContaining(String namePart);

    Issue updateIssueAssignee(Issue issue, User userChangedTo, long userIdWhoChanged);


}
