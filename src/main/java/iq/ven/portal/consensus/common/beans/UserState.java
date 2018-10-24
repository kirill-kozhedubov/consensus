package iq.ven.portal.consensus.common.beans;

import iq.ven.portal.consensus.database.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserState implements Serializable {

    @Autowired
    private ProjectUser projectUser;

    private Set<Role> userRoles;

    private Date logInDate;

    public Set<Role> getUserRole() {
        return userRoles;
    }

    public void setUserRole(Set<Role> userRoles) {
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
