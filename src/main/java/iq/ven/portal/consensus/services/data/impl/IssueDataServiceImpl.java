package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.HistoryEntry;
import iq.ven.portal.consensus.database.HistoryRepository;
import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.issue.model.IssueStatuses;
import iq.ven.portal.consensus.database.issue.repository.IssueRepository;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.database.user.repository.UserRepository;
import iq.ven.portal.consensus.services.data.IssueDataService;
import iq.ven.portal.consensus.services.data.ProjectDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("issuesService")
public class IssueDataServiceImpl implements IssueDataService {

    private static final Logger logger = LoggerFactory.getLogger(IssueDataServiceImpl.class);

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ProjectDataService projectDataService;


    @Override
    public Issue saveIssue(Issue issue) {
        try {
            HistoryEntry historyEntry = new HistoryEntry(issue.getReporters().get(0), issue, "Issue created by " + issue.getReporters().get(0));
            issue.getHistory().add(historyEntry);
            if (issue.getAssignee() != null && issue.getReporters() != null && issue.getReporters().get(0) != null) {
                User assignee = userRepository.findById(issue.getAssignee().getId());
                User reporter = userRepository.findById(issue.getReporters().get(0).getId());

                issue.setAssignee(assignee);
                issue.setReporters(Arrays.asList(assignee));
            }
            Issue savedIssue = issueRepository.save(issue);
            logger.info("Saved issue:::", savedIssue);
            Issue savedFetchedIssue = issueRepository.findById(savedIssue.getId());
            return savedIssue;
        } catch (Exception e) {
            return null;
        }


    }


    @Override
    public Issue saveIssue(Issue issue, Board board, Project project) {
        try {
            HistoryEntry historyEntry = new HistoryEntry(issue.getReporters().get(0), issue, "Issue created by " + issue.getReporters().get(0));
            issue.getHistory().add(historyEntry);
            if (issue.getAssignee() != null && issue.getReporters() != null && issue.getReporters().get(0) != null) {
                User assignee = userRepository.findById(issue.getAssignee().getId());
             //   User reporter = userRepository.findById(issue.getReporters().get(0).getId());

                issue.setAssignee(assignee);
                issue.setReporters(Arrays.asList(assignee));
            }

            if (board != null) {
                issue.setBoard(board);
                issue.setBoardColumn(board.getColumns().get(0));
            }

            if (project != null) {
                issue.setProject(project);
            }

            Issue savedIssue = issueRepository.save(issue);
            logger.info("Saved issue:::", savedIssue);
            Issue savedFetchedIssue = issueRepository.findById(savedIssue.getId());
            return savedIssue;
        } catch (Exception e) {
            return null;
        }

    }


    @Override
    public Issue findIssueById(Long id) {
        Issue issue = issueRepository.findById(id);

        return issue;
    }

    @Override
    public Issue findIssueByIssueKey(String issueKey) {
        String issueIdString = issueKey.substring(issueKey.lastIndexOf("-") + 1);
        Issue issue;
        try {
            Long issueId = Long.parseLong(issueIdString);
            issue = issueRepository.findById(issueId);
            return issue;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public Issue findIssueByIssueKey(String projectAbbr, Long issueId) {

        Project project = projectDataService.findProjectByAbbreviation(projectAbbr);
        Issue issue;
        try {
            issue = issueRepository.findByIdAndProject(issueId, project);
            return issue;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public List<Issue> findIssueByNameIgnoreCaseContaining(String namePart) {
        try {
            List<Issue> issues = issueRepository.findByNameContainingIgnoreCase(namePart);
            if (issues != null && issues.size() > 0) {
                return issues;
            } else {
                return new ArrayList<Issue>();
            }
        } catch (Exception e) {
            logger.error("findIssueByNameIgnoreCaseContaining:::name:" + namePart, e);
            return new ArrayList<Issue>();
        }


    }

    @Override
    public Issue updateIssueAssignee(Issue issue, User userChangedTo, Long userIdWhoChanged) {
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

    @Override
    public Issue changeStatus(Issue issue, String newStatus, User user) {
        IssueStatuses issueStatus = IssueStatuses.getIssueStatusByName(newStatus);
        issue.setStatus(issueStatus);

        HistoryEntry historyEntry = new HistoryEntry(user, issue, "Issue status changed to " + newStatus);
        issue.getHistory().add(historyEntry);


        return issueRepository.save(issue);
    }

    @Override
    public Issue changeDueDate(Issue issue, Date newDueDate, User user) {
        return null;
    }

    @Override
    public Issue moveToAnotherBoardColumn(Issue issue, String columnName, User user) {
        HistoryEntry historyEntry = new HistoryEntry(user, issue, "Issue moved to " + columnName);
        issue.getHistory().add(historyEntry);

        //todo add actual logic

        issueRepository.save(issue);


        return null;
    }
}
