package iq.ven.portal.consensus.database.user.repository;

import iq.ven.portal.consensus.database.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    Role findById(long id);
}
