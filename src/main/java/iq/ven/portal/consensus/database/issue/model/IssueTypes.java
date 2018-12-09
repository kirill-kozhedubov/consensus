package iq.ven.portal.consensus.database.issue.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IssueTypes {
    USER_STORY("USER STORY", "board_issue_type_user-story", "issue_type_user-story"),
    BUG("BUG", "board_issue_type_bug", "issue_type_bug"),
    PROJECT("PROJECT", "board_issue_type_project", "issue_type_project"),
    QUESTION("QUESTION", "board_issue_type_question", "issue_type_question"),
    DEV_TASK("DEV TASK", "board_issue_type_dev-task", "issue_type_dev-task"),
    WORK_ITEM("WORK ITEM", "board_issue_type_work-item", "issue_type_work-item"),
    DESIGN_TASK("DESIGN TASK", "board_issue_type_design-task", "issue_type_design-task");

    String displayName;
    String boardClass;
    String issueViewClass;

    IssueTypes(String displayName, String boardClass, String issueViewClass) {
        this.displayName = displayName;
        this.boardClass = boardClass;
        this.issueViewClass = issueViewClass;
    }

    public static IssueTypes getIssueTypeByName(String displayName) {
        for (IssueTypes e : IssueTypes.values()) {
            if (e.displayName.equals(displayName)) {
                return e;
            }
        }
        return null;// not found
    }

    public static Map<String, Object> getFullIssueTypeByName(String displayName) {
        Map<String, Object> map = new HashMap<>();

        for (IssueTypes e : IssueTypes.values()) {
            if (e.displayName.equals(displayName)) {
                map.put("displayName", e.displayName);
                map.put("boardClass", e.displayName);
                map.put("issueViewClass", e.displayName);

                return map;
            }
        }
        return null;// not found
    }

    public static List<String> getIssueTypesStringsList() {
        List<String> list = new ArrayList<>();
        for (IssueTypes e : IssueTypes.values()) {
            list.add(e.getDisplayName());
        }
        return list;
    }

    public static List<Map<String, Object>> getIssueTypesFullList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (IssueTypes e : IssueTypes.values()) {
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
