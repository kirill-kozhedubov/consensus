package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.board.repository.BoardColumnRepository;
import iq.ven.portal.consensus.database.board.repository.BoardRepository;
import iq.ven.portal.consensus.database.project.model.Project;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.services.data.BoardsDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardsDataServiceImpl implements BoardsDataService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardColumnRepository boardColumnRepository;

    @Override
    public Board save(Board board) {
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    @Override
    public Board findBoardById(long id) {
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
}
