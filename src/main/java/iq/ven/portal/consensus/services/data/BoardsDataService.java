package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.List;

public interface BoardsDataService {

    Board save(Board board);

    Board findBoardById(long id);

    List<Board> findBoardsByProject(Project project);

    List<Board> findAllBoards();

    List<Board> findBoardsByManager(User user);

}
