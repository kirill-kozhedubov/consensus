package iq.ven.portal.consensus.database.board.model.main;

import java.util.ArrayList;
import java.util.List;

public enum BoardType {
    KANBAN("KANBAN"),
    AGILE("AGILE");

    String displayName;

    BoardType(String displayName) {
        this.displayName = displayName;
    }

    public static BoardType getBoardTypeByName(String displayName) {
        for (BoardType e : BoardType.values()) {
            if (e.displayName.equals(displayName)) {
                return e;
            }
        }
        return null;// not found
    }

    public static List<String> getBoardTypesStringsList() {
        List<String> list = new ArrayList<>();
        for (BoardType e : BoardType.values()) {
            list.add(e.getDisplayName());
        }
        return list;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
