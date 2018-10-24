package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "issue_attachments")
@PrimaryKeyJoinColumn(name = "id")
public class IssueAttachment extends Base {

    private User user;

    private String comment;

    private String attachmentLink; //?

    private Date addedDate;


}
