package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.Base;
import iq.ven.portal.consensus.database.HistoryEntry;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.*;

public class HistoryViewConverter {


    public static Map<String, Object> convertHistoryEntry(HistoryEntry historyEntry, boolean isLightweight) {

        String name = historyEntry.getName();
        String description = historyEntry.getDescription();
        Long id = historyEntry.getId();
        Date createdDate = historyEntry.getCreatedDate();
        Date updatedDate = historyEntry.getUpdatedDate();

        User user = historyEntry.getUser();
        Base entity = historyEntry.getEntity();
        String changeItself = historyEntry.getChangeItself();

        Map<String, Object> historyEntryMap = new HashMap<>();

        if (!isLightweight) {

        }

        Map<String, Object> entityMap = BaseViewConverter.convertBase(entity, isLightweight);
        historyEntryMap.put("entity", entityMap);

        Map<String, Object> userMap = UserViewConverter.convertUser(user, isLightweight);
        historyEntryMap.put("user", userMap);

        historyEntryMap.put("changeItself", changeItself);

        historyEntryMap.put("name", name);
        historyEntryMap.put("description", description);
        historyEntryMap.put("id", id);
        historyEntryMap.put("createdDate", createdDate);
        historyEntryMap.put("updatedDate", updatedDate);

        return historyEntryMap;
    }


    public static List<Map<String, Object>> convertFullHistory(List<HistoryEntry> historyEntries, boolean isLightweight) {
        List<Map<String, Object>> historyEntriesList = new ArrayList<>();

        for (HistoryEntry historyEntry : historyEntries) {
            Map<String, Object> issueMap = convertHistoryEntry(historyEntry, isLightweight);
            historyEntriesList.add(issueMap);
        }

        return historyEntriesList;
    }


}
