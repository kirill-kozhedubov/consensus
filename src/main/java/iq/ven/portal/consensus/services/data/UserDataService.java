package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDataService {

    User findUserByEmail(String email);

    User saveUser(User user, String roleName);

    User saveUser(User user);

    UserDetails loadUserByUsername(String userName);

    User getUserByUsername(String userName);

}
