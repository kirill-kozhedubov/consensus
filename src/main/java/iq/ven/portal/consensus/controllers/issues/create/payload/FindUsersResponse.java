package iq.ven.portal.consensus.controllers.issues.create.payload;

import java.util.List;

public class FindUsersResponse {

    private List<FoundUser> foundUsers;

    public FindUsersResponse() {
    }

    public FindUsersResponse(List<FoundUser> foundUsers) {
        this.foundUsers = foundUsers;
    }

    public List<FoundUser> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<FoundUser> foundUsers) {
        this.foundUsers = foundUsers;
    }
}
