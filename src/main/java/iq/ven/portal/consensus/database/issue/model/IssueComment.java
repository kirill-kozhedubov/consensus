package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "issue_comments")
@PrimaryKeyJoinColumn(name="id")
public class IssueComment extends Base {

    private User commentator;

    private Date addedDate;

    private String text;

}
