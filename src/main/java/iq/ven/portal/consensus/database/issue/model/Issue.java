package iq.ven.portal.consensus.database.issue.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "issues")
@PrimaryKeyJoinColumn(name = "id")
@SequenceGenerator(name="key_sequence", initialValue=1, allocationSize=1)
public class Issue extends Base {

    private String issueKey;

    @Column(name = "assignee")
    private User assignee;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> reporters;

    private Issue parentIssue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "due_date")
    private Date dueDate;

    private IssueTypes type;

    private IssuePriorities priority;

    private IssueStatuses status;//IssueStatuses.values();


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IssueAttachment> attachments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IssueComment> comments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IssueHistoryEntry> history;

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

    @Enumerated(EnumType.STRING)
    public IssueTypes getType() {
        return type;
    }

    public void setType(IssueTypes type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    public IssuePriorities getPriority() {
        return priority;
    }

    public void setPriority(IssuePriorities priority) {
        this.priority = priority;
    }

    @Enumerated(EnumType.STRING)
    public IssueStatuses getStatus() {
        return status;
    }

    public void setStatus(IssueStatuses status) {
        this.status = status;
    }


}
