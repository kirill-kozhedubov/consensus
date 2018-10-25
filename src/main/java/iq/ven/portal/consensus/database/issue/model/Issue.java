package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issues")
@PrimaryKeyJoinColumn(name = "id")
@SequenceGenerator(name="key_sequence", initialValue=1, allocationSize=100)
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

    //issue status


    // Use the sequence that is defined above:
    @Column(name = "issue_key", nullable = false, insertable = false/*, updatable = false*/)
    @Generated(GenerationTime.ALWAYS)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    public String getIssueKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.project.getAbbreviation())
                .append("-")
                .append(issueKey);

        return sb.toString();
    }
    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }
}
