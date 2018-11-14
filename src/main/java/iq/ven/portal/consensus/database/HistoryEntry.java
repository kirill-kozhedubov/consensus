package iq.ven.portal.consensus.database;

import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "history")
@PrimaryKeyJoinColumn(name="id")
public class HistoryEntry extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_entity_id")
    private Base entity;

    private String changeItself;//maybe rework maybe DB maybe from and to

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Base getEntity() {
        return entity;
    }

    public void setEntity(Base entity) {
        this.entity = entity;
    }

    public String getChangeItself() {
        return changeItself;
    }

    public void setChangeItself(String changeItself) {
        this.changeItself = changeItself;
    }
}
