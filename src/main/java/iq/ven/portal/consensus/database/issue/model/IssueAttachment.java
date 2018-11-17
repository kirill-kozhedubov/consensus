package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "issue_attachments")
@PrimaryKeyJoinColumn(name = "id")
public class IssueAttachment extends Base {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attachment_file_id")
    private IssueAttachmentFile issueAttachmentFile;

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

    public IssueAttachmentFile getIssueAttachmentFile() {
        return issueAttachmentFile;
    }

    public void setIssueAttachmentFile(IssueAttachmentFile issueAttachmentFile) {
        this.issueAttachmentFile = issueAttachmentFile;
    }
}
