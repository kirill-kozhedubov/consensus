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


    private String commentContent;

    private boolean isImage;

    private String attachmentLink;




}
