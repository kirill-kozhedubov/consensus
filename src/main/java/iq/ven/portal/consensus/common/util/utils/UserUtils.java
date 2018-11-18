package iq.ven.portal.consensus.common.util.utils;

import iq.ven.portal.consensus.database.user.model.User;

public class UserUtils {

    public static String convertUserFullNameWithUsername(User user) {
        String fullNameWithUserName = null;
        if (user != null) {
            fullNameWithUserName = user.getFullName() + " (" + user.getUsername() + ")";
        }
        return fullNameWithUserName;
    }


}
