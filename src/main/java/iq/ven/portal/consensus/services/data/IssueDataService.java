package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.issue.model.Issue;

public interface IssueDataService {

    Issue saveIssue(Issue issue);

    Issue getIssueByKey(String issueKey);

    Issue getIssueById(long id);

}
