package iq.ven.portal.consensus.database.project.model;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.issue.model.Issue;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@PrimaryKeyJoinColumn(name = "id")
public class Project extends Base {

    @Column(name = "abbreviation", unique = true, nullable = false)
    private String abbreviation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "project_issues")
    private List<Issue> issues;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "project_boards")
    private List<Board> boards;

    public Project() {
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
