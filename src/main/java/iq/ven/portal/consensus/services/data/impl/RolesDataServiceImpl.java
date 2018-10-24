package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.user.model.Role;
import iq.ven.portal.consensus.database.user.repository.RoleRepository;
import iq.ven.portal.consensus.database.user.repository.UserRepository;
import iq.ven.portal.consensus.services.data.RolesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolesService")
public class RolesDataServiceImpl implements RolesDataService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        Role role = roleRepository.findByRole(name);
        return role;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
