package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDataService {
    User findUserByEmail(String email);

    void saveUser(User user);

    UserDetails loadUserByUsername(String userName);
}
