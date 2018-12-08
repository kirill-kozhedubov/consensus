package iq.ven.portal.consensus.database.user.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@PrimaryKeyJoinColumn(name = "id")
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

}
