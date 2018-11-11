package iq.ven.portal.consensus.database.user.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@PrimaryKeyJoinColumn(name="id")
public class Role extends Base {

    public Role() {
    }

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

/*

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUser(Set<User> users) {
        this.users = users;
    }
*/

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> user;


}
