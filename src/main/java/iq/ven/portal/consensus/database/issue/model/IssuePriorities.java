package iq.ven.portal.consensus.database.issue.model;

public enum IssuePriorities {
    CRITICAL("CRITICAL"),
    HIGH("HIGH"),
    LOW("LOW"),
    NORMAL("NORMAL"),
    BLOCKER("BLOCKER");

    String displayName;

    IssuePriorities(String displayName) {
        this.displayName = displayName;
    }

    public static IssuePriorities getIssuePriorityByName(String displayName) {
        for (IssuePriorities e : IssuePriorities.values()) {
            if (e.displayName.equals(displayName)) {
                return e;
            }
        }
        return null;// not found
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
