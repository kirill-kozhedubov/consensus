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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
