package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.issue.model.Issue;

import java.util.List;

public interface IssueDataService {

    Issue saveIssue(Issue issue);

    Issue findIssueById(long id);

    List<Issue> findIssueByNameIgnoreCaseContaining(String namePart);

}
