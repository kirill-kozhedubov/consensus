package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.board.model.main.Board;
import iq.ven.portal.consensus.database.board.model.main.BoardColumn;
import iq.ven.portal.consensus.database.issue.model.Issue;

import java.util.*;

public class BoardViewConverter {

    public static Map<String, Object> convertBoard(Board board, boolean isLightweight) {

        Long boardId = board.getId();
        String boardName = board.getName();
        String boardDescription = board.getDescription();
        Boolean isVisible = board.isVisible();
        List<BoardColumn> boardColumns = board.getColumns();
        Date createdDate = board.getCreatedDate();
        Date updatedDate = board.getUpdatedDate();

        Map<String, Object> boardMap = new HashMap<>();

        // board columns
        List<Map<String, Object>> boardColumnsList = new ArrayList<>();
        for (BoardColumn boardColumn : boardColumns) {
            Map<String, Object> boardColumnMap = convertBoardColumn(boardColumn, isLightweight);
            boardColumnsList.add(boardColumnMap);
        }

        if (!isLightweight) {

        }
        boardMap.put("id", boardId);
        boardMap.put("name", boardName);
        boardMap.put("description", boardDescription);
        boardMap.put("isVisible", isVisible);
        boardMap.put("columns", boardColumnsList);
        boardMap.put("createdDate", createdDate);
        boardMap.put("updatedDate", updatedDate);

        return boardMap;
    }


    public static Map<String, Object> convertBoardColumn(BoardColumn boardColumn, boolean isLightweight) {

        Long columnId = boardColumn.getId();
        String columnName = boardColumn.getName();
        String columnDescription = boardColumn.getDescription();
        Boolean isVisible = boardColumn.isVisible();
        Long position = boardColumn.getPosition();
        Date createdDate = boardColumn.getCreatedDate();
        Date updatedDate = boardColumn.getUpdatedDate();

        List<Issue> issues = boardColumn.getIssues();
        List<Map<String, Object>> issuesList = IssueViewConverter.convertIssues(issues, isLightweight);


        Map<String, Object> columnMap = new HashMap<>();

        columnMap.put("id", columnId);
        columnMap.put("name", columnName);
        columnMap.put("description", columnDescription);
        columnMap.put("isVisible", isVisible);
        columnMap.put("issues", issuesList);
        columnMap.put("position", position);
        columnMap.put("createdDate", createdDate);
        columnMap.put("updatedDate", updatedDate);

        return columnMap;
    }


}
