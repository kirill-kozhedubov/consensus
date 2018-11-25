package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.board.repository.BoardColumnRepository;
import iq.ven.portal.consensus.database.board.repository.BoardRepository;
import iq.ven.portal.consensus.services.data.BoardColumnsDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardColumnsDataServiceImpl implements BoardColumnsDataService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardColumnRepository boardColumnRepository;


}
