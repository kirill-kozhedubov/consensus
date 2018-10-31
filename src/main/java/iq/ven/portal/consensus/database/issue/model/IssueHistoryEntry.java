package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "issues_history")
@PrimaryKeyJoinColumn(name="id")
public class IssueHistoryEntry extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;


    private String changeItself;//maybe rework maybe DB maybe from and to



}
