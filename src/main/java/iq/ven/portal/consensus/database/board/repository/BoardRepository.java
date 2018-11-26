package iq.ven.portal.consensus.database.board.repository;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardRepository")
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findById(Long id);

    List<Board> findByManagers(User managers);

    List<Board> findByProject(Project project);

}
