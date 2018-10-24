package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "issues")
@PrimaryKeyJoinColumn(name = "id")
public class Issue extends Base {

    private String issueKey;

    private String description;

    private User assignee;

    private User reporter;

    private Issue parentIssue;

    private Project project;

    private Date createdDate;

    private Date updatedDate;

    private Date dueDate;

    //issue type

    //issue priority

}
