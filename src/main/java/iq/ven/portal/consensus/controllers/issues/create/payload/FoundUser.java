package iq.ven.portal.consensus.controllers.issues.create.payload;

public class FoundUser {

    private String fullName;

    private String username;

    private String avatar;

    public FoundUser() {

    }

    public FoundUser(String fullName, String username, String avatar) {
        this.fullName = fullName;
        this.username = username;
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
