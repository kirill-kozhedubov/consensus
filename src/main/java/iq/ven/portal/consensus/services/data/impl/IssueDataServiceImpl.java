package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.repository.IssueRepository;
import iq.ven.portal.consensus.services.data.IssueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("issuesService")
public class IssueDataServiceImpl implements IssueDataService {

    @Autowired
    private IssueRepository issueRepository;

 /*   @Autowired
    private Issue Repository;

    @Autowired
    private Issue Repository;

    @Autowired
    private Issue Repository;*/


    @Override
    public Issue storeIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public Issue getIssueByKey(String issueKey) {
        Issue issue = issueRepository.findByIssueKey(issueKey);

        return issue;
    }

    @Override
    public Issue getIssueById(long id) {
        Issue issue = issueRepository.findById(id);

        return issue;
    }
}
