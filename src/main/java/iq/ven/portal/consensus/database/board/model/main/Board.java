package iq.ven.portal.consensus.database.board.model.main;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board extends Base implements Serializable {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<User> managers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardColumn> columns;

    @OneToOne(fetch = FetchType.LAZY)
    private Project project;

    public Board() {
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }

    public List<BoardColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<BoardColumn> columns) {
        this.columns = columns;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
