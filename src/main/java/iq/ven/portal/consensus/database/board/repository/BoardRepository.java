package iq.ven.portal.consensus.database.board.repository;

import iq.ven.portal.consensus.database.board.model.main.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("boardRepository")
public interface BoardRepository  extends JpaRepository<Board, Long> {

}
