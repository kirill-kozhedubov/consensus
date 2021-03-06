package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.board.repository.BoardColumnRepository;
import iq.ven.portal.consensus.database.board.repository.BoardRepository;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.database.user.repository.UserRepository;
import iq.ven.portal.consensus.services.data.BoardsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("boardsService")
public class BoardsDataServiceImpl implements BoardsDataService {

    private static final Logger logger = LoggerFactory.getLogger(BoardsDataServiceImpl.class);

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardColumnRepository boardColumnRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Board save(Board board) {
        try {
            if (board.getManagers() != null && board.getManagers().get(0) != null) {
                User manager = board.getManagers().get(0);
                manager = userRepository.findById(manager.getId());
                board.setManagers(Arrays.asList(manager));
            }

            Board savedBoard = boardRepository.save(board);

            logger.info("Saved Board:::", savedBoard);
            return savedBoard;
        } catch (Exception e) {
            logger.error("Board not created ", e);
            return null;
        }
    }

    @Override
    public Board findBoardById(Long id) {
        Board boardById = boardRepository.findById(id);
        return boardById;
    }

    @Override
    public List<Board> findBoardsByProject(Project project) {
        return null;
    }

    @Override
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findBoardsByManager(User user) {
        return null;
    }

    @Override
    public Board findBoardByName(String name) {
        Board boardByName = boardRepository.findBoardByName(name);
        return boardByName;
    }
}
