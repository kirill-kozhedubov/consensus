package iq.ven.portal.consensus.common.beans;

import iq.ven.portal.consensus.database.user.model.Role;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserState implements Serializable {

    @Autowired
    private ProjectUser projectUser;

    @Autowired
    private UserDataService userDataService;

    private List<Role> userRoles;

    private Date logInDate;

    public List<Role> getUserRole() {
        return userRoles;
    }

    public User getUser() {
        return userDataService.findUserById(projectUser.getUserData().getUserId());
    }

    public void setUserRole(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Date getLogInDate() {
        return logInDate;
    }

    public void setLogInDate(Date logInDate) {
        this.logInDate = logInDate;
    }

    public void clear() {

    }

}
