package iq.ven.portal.consensus.database.issue.model;

public enum IssueStatuses {
    CLOSED("CLOSED"),
    IN_PROGRESS("IN PROGRESS"),
    READY_FOR_TESTING("READY FOR TESTING"),
    INFO_REQUIRED("INFO REQUIRED"),
    INFO_RECEIVED("INFO RECEIVED"),
    OPEN("OPEN"),
    IN_ASSESSMENT("IN ASSESSMENT"),
    ASSESSED("ASSESSED"),
    REOPENED("REOPENED");

    String displayName;

    IssueStatuses(String displayName) {
        this.displayName = displayName;
    }

    public static IssueStatuses getIssueStatusByName(String displayName) {
        for (IssueStatuses e : IssueStatuses.values()) {
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
