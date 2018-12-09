package iq.ven.portal.consensus.database.issue.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IssueStatuses {
    CLOSED("CLOSED", "board_issue_status_closed", "issue_status_closed"),
    IN_PROGRESS("IN PROGRESS", "board_issue_status_in-progress", "issue_status_in-progress"),
    READY_FOR_TESTING("READY FOR TESTING", "board_issue_status_rft", "issue_status_rft"),
    INFO_REQUIRED("INFO REQUIRED", "board_issue_status_info-required", "issue_status_info-required"),
    INFO_RECEIVED("INFO RECEIVED", "board_issue_status_info-received", "issue_status_info-received"),
    OPEN("OPEN", "board_issue_status_open", "issue_status_open"),
    IN_ASSESSMENT("IN ASSESSMENT", "board_issue_status_in-assessment", "issue_status_in-assessment"),
    ASSESSED("ASSESSED", "board_issue_status_assessed", "issue_status_assessed"),
    REOPENED("REOPENED", "board_issue_status_reopened", "issue_status_reopened");

    String displayName;
    String boardClass;
    String issueViewClass;

    IssueStatuses(String displayName, String boardClass, String issueViewClass) {
        this.displayName = displayName;
        this.boardClass = boardClass;
        this.issueViewClass = issueViewClass;
    }

    public static IssueStatuses getIssueStatusByName(String displayName) {
        for (IssueStatuses e : IssueStatuses.values()) {
            if (e.displayName.equals(displayName)) {
                return e;
            }
        }
        return null;// not found
    }

    public static List<String> getIssueStatusesStringsList() {
        List<String> list = new ArrayList<>();
        for (IssueStatuses e : IssueStatuses.values()) {
            list.add(e.getDisplayName());
        }
        return list;
    }

    public static Map<String, Object> getFullIssueStatusByName(String displayName) {
        Map<String, Object> map = new HashMap<>();

        for (IssueStatuses e : IssueStatuses.values()) {
            if (e.displayName.equals(displayName)) {
                map.put("displayName", e.displayName);
                map.put("boardClass", e.displayName);
                map.put("issueViewClass", e.displayName);

                return map;
            }
        }
        return null;// not found
    }


    public static List<Map<String, Object>> getIssueStatusesFullList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (IssueStatuses e : IssueStatuses.values()) {
            Map<String, Object> map = new HashMap<>();

            map.put("name", e.getDisplayName());
            map.put("boardClass", e.getBoardClass());
            map.put("issueViewClass", e.getIssueViewClass());

            list.add(map);
        }
        return list;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBoardClass() {
        return boardClass;
    }

    public String getIssueViewClass() {
        return issueViewClass;
    }

    @Override
    public String toString() {
        return displayName;
    }


}
