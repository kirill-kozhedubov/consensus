package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.board.repository.BoardColumnRepository;
import iq.ven.portal.consensus.database.board.repository.BoardRepository;
import iq.ven.portal.consensus.services.data.BoardColumnsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardColumnsDataServiceImpl implements BoardColumnsDataService {

    private static final Logger logger = LoggerFactory.getLogger(BoardsDataServiceImpl.class);

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardColumnRepository boardColumnRepository;


}
