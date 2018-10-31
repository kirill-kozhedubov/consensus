package iq.ven.portal.consensus.database.board.model.main;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.issue.model.Issue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "board_columns")
public class BoardColumn extends Base{

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issues;


}
