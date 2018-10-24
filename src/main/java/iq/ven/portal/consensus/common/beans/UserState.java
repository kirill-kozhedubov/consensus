package iq.ven.portal.consensus.common.beans;

import iq.ven.portal.consensus.database.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserState implements Serializable {

    @Autowired
    private ProjectUser projectUser;

    private Role userRole;

    private Date logInDate;

    public ProjectUser getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(ProjectUser projectUser) {
        this.projectUser = projectUser;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
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
