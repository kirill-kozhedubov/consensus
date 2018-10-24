package iq.ven.portal.consensus.services.data;

import iq.ven.portal.consensus.database.user.model.Role;

public interface RolesDataService {

    Role findRoleByName(String name);

    void saveRole(Role role);

}
