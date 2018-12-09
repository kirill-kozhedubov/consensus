package iq.ven.portal.consensus.database.issue.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IssuePriorities {
    CRITICAL("CRITICAL", "board_issue_priority_critical", "issue_priority_critical"),
    HIGH("HIGH", "board_issue_priority_high", "issue_priority_high"),
    LOW("LOW", "board_issue_priority_low", "issue_priority_low"),
    NORMAL("NORMAL", "board_issue_priority_normal", "issue_priority_normal"),
    BLOCKER("BLOCKER", "board_issue_priority_blocker", "issue_priority_blocker");

    String displayName;
    String boardClass;
    String issueViewClass;

    IssuePriorities(String displayName, String boardClass, String issueViewClass) {
        this.displayName = displayName;
        this.boardClass = boardClass;
        this.issueViewClass = issueViewClass;
    }

    public static IssuePriorities getIssuePriorityByName(String displayName) {
        for (IssuePriorities e : IssuePriorities.values()) {
            if (e.displayName.equals(displayName)) {
                return e;
            }
        }
        return null;// not found
    }

    public static Map<String, Object> getFullIssuePriorityByName(String displayName) {
        Map<String, Object> map = new HashMap<>();

        for (IssuePriorities e : IssuePriorities.values()) {
            if (e.displayName.equals(displayName)) {
                map.put("displayName", e.displayName);
                map.put("boardClass", e.displayName);
                map.put("issueViewClass", e.displayName);

                return map;
            }
        }
        return null;// not found
    }

    public static List<String> getIssuePrioritiesStringsList() {
        List<String> list = new ArrayList<>();
        for (IssuePriorities e : IssuePriorities.values()) {
            list.add(e.getDisplayName());
        }
        return list;
    }

    public static List<Map<String, Object>> getIssuePrioritiesFullList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (IssuePriorities e : IssuePriorities.values()) {
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
