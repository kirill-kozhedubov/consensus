package iq.ven.portal.consensus.database.user.repository;

import iq.ven.portal.consensus.database.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String usernamePart);
}
