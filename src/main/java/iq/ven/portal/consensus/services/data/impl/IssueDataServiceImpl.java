package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.HistoryEntry;
import iq.ven.portal.consensus.database.HistoryRepository;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.repository.IssueRepository;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.database.user.repository.UserRepository;
import iq.ven.portal.consensus.services.data.IssueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("issuesService")
public class IssueDataServiceImpl implements IssueDataService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;



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
    public Issue findIssueByIssueKey(String issueKey) {
        String issueIdString = issueKey.replaceAll("\\D+", "");
        Issue issue;
        try {
            long issueId = Long.parseLong(issueIdString);
            issue = issueRepository.findById(issueId);
            return issue;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public List<Issue> findIssueByNameIgnoreCaseContaining(String namePart) {
        List<Issue> issues = issueRepository.findByNameContainingIgnoreCase(namePart);

        return issues;
    }

    @Override
    public Issue updateIssueAssignee(Issue issue, User userChangedTo, long userIdWhoChanged) {
        issue.setAssignee(userChangedTo);
        User user = userRepository.findById(userIdWhoChanged);

        HistoryEntry historyEntry = new HistoryEntry();
        historyEntry.setChangeItself("Assignee changed to " + userChangedTo.getFullName() + " (" + userChangedTo.getUsername() + ")");
        historyEntry.setEntity(issue);
        historyEntry.setUser(user);

        historyEntry = historyRepository.save(historyEntry);

        issue.getHistory().add(historyEntry);


        Issue issueSaved = issueRepository.save(issue);
        return issueSaved;
    }
}
