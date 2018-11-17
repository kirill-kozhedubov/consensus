package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.user.model.Role;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserViewConverter {

    public static Map<String, Object> convertUser(User user, boolean isLightweight) {

        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = user.getLastName();
        String email = user.getEmail();
        boolean isActive = user.isActive();
        List<Role> userRoles = user.getRoles();


        Map<String, Object> userMap = new HashMap<>();
        List<String> rolesList = convertRoles(userRoles);

        userMap.put("username", username);
        userMap.put("firstName", firstName);
        userMap.put("lastName", lastName);
        userMap.put("fullName", fullName);
        userMap.put("email", email);
        userMap.put("isActive", isActive);
        userMap.put("roles", rolesList);

        return userMap;
    }


    public static List<Map<String, Object>> convertUsers(List<User> user) {
        List<Map<String, Object>> usersList = new ArrayList<>();







        return usersList;
    }

    private static List<String> convertRoles(List<Role> roles) {
        List<String> rolesList = new ArrayList<>();

        for (Role role : roles) {
            String roleName = role.getRole();
            rolesList.add(roleName);
        }

        return rolesList;
    }


}
