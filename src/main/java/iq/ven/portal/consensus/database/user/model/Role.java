package iq.ven.portal.consensus.database.user.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.*;

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

    @ManyToOne
    private User user;


}
