package iq.ven.portal.consensus.common.viewconvertors;

import iq.ven.portal.consensus.database.user.model.Role;
import iq.ven.portal.consensus.database.user.model.User;

import java.util.*;

public class UserViewConverter {

    public static Map<String, Object> convertUser(User user, boolean isLightweight) {

        Long userId = user.getId();
        String username = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = user.getFullName();
        String email = user.getEmail();
        Boolean isActive = user.isActive();
        List<Role> userRoles = user.getRoles();
        Date createdDate = user.getCreatedDate();
        Date updatedDate = user.getUpdatedDate();

        Map<String, Object> userMap = new HashMap<>();
        List<String> rolesList = convertRoles(userRoles);

        userMap.put("username", username);
        userMap.put("firstName", firstName);
        userMap.put("lastName", lastName);
        userMap.put("fullName", fullName);
        userMap.put("email", email);
        userMap.put("isActive", isActive);
        userMap.put("roles", rolesList);
        userMap.put("id", userId);
        userMap.put("createdDate", createdDate);
        userMap.put("updatedDate", updatedDate);

        return userMap;
    }


    public static List<Map<String, Object>> convertUsers(List<User> users, boolean isLightweight) {
        List<Map<String, Object>> usersList = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> userMap = convertUser(user, isLightweight);
            usersList.add(userMap);
        }

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
