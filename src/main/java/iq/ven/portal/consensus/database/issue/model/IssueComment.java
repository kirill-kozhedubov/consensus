package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;
@Entity
@Table(name = "issue_comments")
@PrimaryKeyJoinColumn(name="id")
public class IssueComment extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(columnDefinition = "text")
    private String text;

}
