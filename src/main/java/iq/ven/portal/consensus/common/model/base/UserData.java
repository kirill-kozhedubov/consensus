package iq.ven.portal.consensus.common.model.base;

public class UserData {

    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public UserData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static final class UserDataBuilder {
        private String email;
        private String firstName;
        private String lastName;
        private String username;
        private String password;

        private UserDataBuilder() {
        }

        public static UserDataBuilder anUserData() {
            return new UserDataBuilder();
        }

        public UserDataBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDataBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDataBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDataBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDataBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserData build() {
            UserData userData = new UserData();
            userData.setEmail(email);
            userData.setFirstName(firstName);
            userData.setLastName(lastName);
            userData.setUsername(username);
            userData.setPassword(password);
            return userData;
        }
    }
}