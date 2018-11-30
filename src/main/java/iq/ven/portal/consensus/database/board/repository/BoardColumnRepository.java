package iq.ven.portal.consensus.database.board.repository;

import iq.ven.portal.consensus.database.board.model.main.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("boardColumnRepository")
public interface BoardColumnRepository  extends JpaRepository<BoardColumn, Long> {



}
