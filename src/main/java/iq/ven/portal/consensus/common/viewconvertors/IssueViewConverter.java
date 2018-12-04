package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.HistoryEntry;
import iq.ven.portal.consensus.database.issue.model.*;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.*;

public class IssueViewConverter {

    public static Map<String, Object> convertIssue(Issue issue, boolean isLightweight) {

        Long issueId = issue.getId();
        String issueKey = issue.getIssueKey();
        String issueName = issue.getName();
        String issueDescription = issue.getDescription();
        User assignee = issue.getAssignee();
        Issue parentIssue = issue.getParentIssue();
        List<User> reporters = issue.getReporters();
        List<Issue> childIssues = issue.getChildIssues();
        List<IssueAttachment> attachments = issue.getAttachments();
        List<IssueComment> comments = issue.getComments();
        Date createdDate = issue.getCreatedDate();
        Date updatedDate = issue.getUpdatedDate();
        Date dueDate = issue.getDueDate();
        IssueTypes issueType = issue.getType();
        IssuePriorities issuePriority = issue.getPriority();
        IssueStatuses issueStatus = issue.getStatus();
        List<HistoryEntry> history = issue.getHistory();


        Map<String, Object> issueMap = new HashMap<>();
        Map<String, Object> assigneeMap = UserViewConverter.convertUser(assignee, true);
        Map<String, Object> parentIssueMap = IssueViewConverter.convertIssue(parentIssue, true);
        if (!isLightweight) {
            List<Map<String, Object>> reportersMap = UserViewConverter.convertUsers(reporters, true);
            List<Map<String, Object>> childIssuesMap = IssueViewConverter.convertIssues(childIssues, true);
            List<Map<String, Object>> attachmentsMap = IssueViewConverter.convertAttachments(attachments);
            List<Map<String, Object>> commentsMap = IssueViewConverter.convertComments(comments);
            List<Map<String, Object>> historyMap = HistoryViewConverter.convertFullHistory(history, false);

            issueMap.put("reporters", reportersMap);
            issueMap.put("childIssues", childIssuesMap);
            issueMap.put("attachments", attachmentsMap);
            issueMap.put("comments", commentsMap);
            issueMap.put("history", historyMap);
        }

        issueMap.put("id", issueId);
        issueMap.put("issueKey", issueKey);
        issueMap.put("name", issueName);
        issueMap.put("description", issueDescription);

        issueMap.put("assignee", assigneeMap);
        issueMap.put("parentIssue", parentIssueMap);
        issueMap.put("createdDate", createdDate);
        issueMap.put("updatedDate", updatedDate);
        issueMap.put("dueDate", dueDate);
        issueMap.put("issueType", issueType);
        issueMap.put("issuePriority", issuePriority);
        issueMap.put("issueStatus", issueStatus);

        return issueMap;
    }


    public static List<Map<String, Object>> convertIssues(List<Issue> issues, boolean isLightweight) {
        List<Map<String, Object>> issuesList = new ArrayList<>();

        for (Issue issue : issues) {
            Map<String, Object> issueMap = convertIssue(issue, isLightweight);
            issuesList.add(issueMap);
        }

        return issuesList;
    }

    public static Map<String, Object> convertComment(IssueComment comment) {
        String name = comment.getName();
        String description = comment.getDescription();
        Long commentId = comment.getId();
        Date createdDate = comment.getCreatedDate();
        Date updatedDate = comment.getUpdatedDate();
        User commentUser = comment.getUser();
        String commentText = comment.getText();
        Issue commentIssue = comment.getIssue();

        Map<String, Object> commentMap = new HashMap<>();
        Map<String, Object> userMap = UserViewConverter.convertUser(commentUser, true);
        Map<String, Object> issueMap = IssueViewConverter.convertIssue(commentIssue, true);
        commentMap.put("commentUser", userMap);
        commentMap.put("issue", issueMap);
        commentMap.put("name", name);
        commentMap.put("description", description);
        commentMap.put("id", commentId);
        commentMap.put("createdDate", createdDate);
        commentMap.put("updatedDate", updatedDate);
        commentMap.put("commentText", commentText);


        return commentMap;
    }

    public static Map<String, Object> convertAttachment(IssueAttachment attachment) {
        String attachmentName = attachment.getName();
        String attachmentDescription = attachment.getDescription();
        Date createdDate = attachment.getCreatedDate();
        Date updatedDate = attachment.getUpdatedDate();
        User attachmentUser = attachment.getUser();
        Issue attachmentIssue = attachment.getIssue();

        IssueAttachmentFile issueAttachmentFile = attachment.getIssueAttachmentFile();
        String issueAttachmentFileUUID = issueAttachmentFile.getUuid();
        String issueAttachmentFileName = issueAttachmentFile.getFileName();
        String issueAttachmentFileType = issueAttachmentFile.getFileType();
        String issueAttachmentFileSize = issueAttachmentFile.getFileSize();

        Map<String, Object> attachmentMap = new HashMap<>();
        Map<String, Object> attachmentUserMap = UserViewConverter.convertUser(attachmentUser, true);
        Map<String, Object> attachmentIssueMap = IssueViewConverter.convertIssue(attachmentIssue, true);

        attachmentMap.put("name", attachmentName);
        attachmentMap.put("description", attachmentDescription);
        attachmentMap.put("createdDate", createdDate);
        attachmentMap.put("updatedDate", updatedDate);
        attachmentMap.put("user", attachmentUserMap);
        attachmentMap.put("issue", attachmentIssueMap);
        attachmentMap.put("fileUUID", issueAttachmentFileUUID);
        attachmentMap.put("fileName", issueAttachmentFileName);
        attachmentMap.put("fileType", issueAttachmentFileType);
        attachmentMap.put("fileSize", issueAttachmentFileSize);

        return attachmentMap;
    }

    public static List<Map<String, Object>> convertComments(List<IssueComment> comments) {
        List<Map<String, Object>> issueCommentsList = new ArrayList<>();

        for (IssueComment issueComment : comments) {
            Map<String, Object> commentMap = convertComment(issueComment);
            issueCommentsList.add(commentMap);
        }

        return issueCommentsList;
    }

    public static List<Map<String, Object>> convertAttachments(List<IssueAttachment> attachments) {
        List<Map<String, Object>> attachmentsList = new ArrayList<>();

        for (IssueAttachment attachment : attachments) {
            Map<String, Object> attachmentMap = convertAttachment(attachment);
            attachmentsList.add(attachmentMap);
        }

        return attachmentsList;
    }


}
