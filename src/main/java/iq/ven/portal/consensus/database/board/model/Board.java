package iq.ven.portal.consensus.database.board.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "boards")
@PrimaryKeyJoinColumn(name="id")
public class Board extends Base{


    private BoardType boardType;
}
