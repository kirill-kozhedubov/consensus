package iq.ven.portal.consensus.database.issue.model;

public enum IssueTypes {
    USER_STORY("USER STORY"),
    BUG("BUG"),
    PROJECT("PROJECT"),
    QUESTION("QUESTION"),
    DEV_TASK("DEV TASK"),
    WORK_ITEM("WORK ITEM"),
    DESIGN_TASK("DESIGN TASK");

    String displayName;

    IssueTypes(String displayName) {
        this.displayName = displayName;
    }

    public static IssueTypes getIssueTypeByName(String displayName) {
        for (IssueTypes e : IssueTypes.values()) {
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
