package iq.ven.portal.consensus.controllers.kanbanboard.create;

import iq.ven.portal.consensus.common.util.helpers.BoardsHelper;
import iq.ven.portal.consensus.common.util.helpers.TemplatesHelper;
import iq.ven.portal.consensus.controllers.AbstractController;
import iq.ven.portal.consensus.controllers.kanbanboard.create.payload.CreateBoardRequest;
import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.board.model.main.BoardColumn;
import iq.ven.portal.consensus.database.board.model.main.BoardType;
import iq.ven.portal.consensus.database.project.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boards")
public class BoardCreateResourceController extends AbstractController {


    @RequestMapping(path = "/create-board-request", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateAssigneeOnIssue(HttpServletRequest httpServletRequest,
                                                     HttpServletResponse httpServletResponse,
                                                     @Valid CreateBoardRequest createBoardRequest) {
        Map<String, Object> result = new HashMap<>();

        Board board = new Board();
        board.setName(createBoardRequest.getName());
        board.setBoardType(BoardType.KANBAN);
        board.setDescription(createBoardRequest.getDescription());

        List<BoardColumn> standardColumns = BoardsHelper.generateStandardColumnsForBoard();
        board.setColumns(standardColumns);

        Project project = projectDataService.findProjectById(TemplatesHelper.transformStringToLong(createBoardRequest.getProjectId()));
        board.setProject(project);


        Board savedBoard = boardsDataService.save(board);
        if (savedBoard != null) {
            result.put("success", true);
            result.put("boardId", savedBoard.getId());
        } else {
            result.put("error", true);
        }

        return result;
    }


}
