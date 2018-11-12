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



}
