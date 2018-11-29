package iq.ven.portal.consensus.controllers.kanbanboard;

import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.common.viewconvertors.BoardViewConverter;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.database.board.model.main.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/boards")
public class BoardViewController extends AbstractController {

    @RequestMapping(value = {"/board"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                              @RequestParam(required = true, value = "id") String boardId) {
        ModelAndView modelAndView = new ModelAndView();
        //find board in db
        Board board = boardsDataService.findBoardById(TemplatesHelper.transformStringToLong(boardId));
        if (board != null) {
            String boardName = board.getName();
            Map<String, Object> boardMap = BoardViewConverter.convertBoard(board, false);

            modelAndView.addObject("board", boardMap);

            modelAndView.addObject(TemplatesHelper.PAGE_TITLE, boardName);
            modelAndView.setViewName("board/board");

            return modelAndView;
        } else {
            return redirectToPageNotFound();
        }


    }


    @RequestMapping(value = {"/boardd"}, method = RequestMethod.GET)
    public ModelAndView issue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject(TemplatesHelper.PAGE_TITLE, "board");
            modelAndView.setViewName("board/board");
            return modelAndView;


    }




}
