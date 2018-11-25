package iq.ven.portal.consensus.common.util.helpers;

import iq.ven.portal.consensus.database.board.model.main.BoardColumn;

import java.util.*;

public class BoardsHelper {

    public static List<BoardColumn> generateStandardColumnsForBoard() {
        List<BoardColumn> columns = new ArrayList<>();

        for (long i = 1L; i < 7L; i++) {
            BoardColumn column = new BoardColumn();
            column.setPosition(i);
            column.setName(getName(i));
            column.setIssues(Collections.emptyList());
        }


        return columns;
    }


    private static String getName(long position) {
        Map<Long, String> namesOfPositions = new HashMap<>();
        namesOfPositions.put(1L, "TO DO");
        namesOfPositions.put(2L, "IN PROGRESS");
        namesOfPositions.put(3L, "READY FOR BUILD");
        namesOfPositions.put(4L, "READY FOR TESTING");
        namesOfPositions.put(5L, "IN QA");
        namesOfPositions.put(6L, "DONE");
        // namesOfPositions.put(1L, "");
        return namesOfPositions.get(position);
    }


}
