package iq.ven.portal.consensus.database.board.model.main;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board extends Base implements Serializable {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardColumn> columns;

}
