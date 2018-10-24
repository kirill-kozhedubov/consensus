package iq.ven.portal.consensus.database.user.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@PrimaryKeyJoinColumn(name="id")
public class Role extends Base {
    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
