package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.Base;

import java.util.*;

public class BaseViewConverter {


    public static Map<String, Object> convertBase(Base base, boolean isLightweight) {

        Long id = base.getId();
        String baseName = base.getName();
        String baseDescription = base.getDescription();
        Date createdDate = base.getCreatedDate();
        Date updatedDate = base.getUpdatedDate();

        Map<String, Object> baseMap = new HashMap<>();

        if (!isLightweight) {

        }

        baseMap.put("id", id);
        baseMap.put("name", baseName);
        baseMap.put("description", baseDescription);
        baseMap.put("createdDate", createdDate);
        baseMap.put("updatedDate", updatedDate);

        return baseMap;
    }


    public static List<Map<String, Object>> convertBaseList(List<Base> baseList, boolean isLightweight) {
        List<Map<String, Object>> basesMapsList = new ArrayList<>();

        for (Base base : baseList) {
            Map<String, Object> baseMap = convertBase(base, isLightweight);
            basesMapsList.add(baseMap);
        }

        return basesMapsList;
    }


}
