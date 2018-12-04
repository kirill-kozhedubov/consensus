package iq.ven.portal.consensus.database.board.model.main;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.issue.model.Issue;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "board_columns")
public class BoardColumn extends Base {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues;

    @Column(name = "column_position")
    private Long position;

    public BoardColumn() {
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}

