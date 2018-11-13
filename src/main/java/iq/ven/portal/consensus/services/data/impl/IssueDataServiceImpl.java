package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.repository.IssueRepository;
import iq.ven.portal.consensus.services.data.IssueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("issuesService")
public class IssueDataServiceImpl implements IssueDataService {

    @Autowired
    private IssueRepository issueRepository;


    @Override
    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }


    @Override
    public Issue findIssueById(long id) {
        Issue issue = issueRepository.findById(id);

        return issue;
    }

    @Override
    public List<Issue> findIssueByNameIgnoreCaseContaining(String namePart) {
        List<Issue> issue = issueRepository.findByNameContainingIgnoreCase(namePart);

        return issue;
    }
}
