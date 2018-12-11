package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.Date;
import java.util.List;

public interface IssueDataService {

    Issue saveIssue(Issue issue);

    Issue saveIssue(Issue issue, Board board, Project project);

    Issue findIssueById(Long id);

    Issue findIssueByIssueKey(String issueKey);

    Issue findIssueByIssueKey(String projectAbbr, Long issueId);

    List<Issue> findIssueByNameIgnoreCaseContaining(String namePart);

    Issue updateIssueAssignee(Issue issue, User userChangedTo, Long userIdWhoChanged);

    Issue changeStatus(Issue issue, String newStatus, User user);

    Issue changeDueDate(Issue issue, Date newDueDate, User user);

    Issue moveToAnotherBoardColumn(Issue issue, String columnName, User user);

}
