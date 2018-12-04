package iq.ven.portal.consensus.services.data.impl;

import iq.ven.portal.consensus.database.user.model.Role;
import iq.ven.portal.consensus.database.user.model.User;
import iq.ven.portal.consensus.database.user.repository.RoleRepository;
import iq.ven.portal.consensus.database.user.repository.UserRepository;
import iq.ven.portal.consensus.services.data.RolesDataService;
import iq.ven.portal.consensus.services.data.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userService")
public class UserDataServiceImpl implements UserDataService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolesDataService rolesDataService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserDataServiceImpl.class);

    @Override
    public User findUserByEmail(String email) {

        try {
            User foundUser = userRepository.findByEmail(email);
            return foundUser;
        } catch (Exception e) {
            logger.error("Error fetching user by email::", e);
            return null;
        }


    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role role = rolesDataService.findRoleByName("USER");
        if (role == null) {
            Role userRole = new Role();
            userRole.setRole("USER");
            rolesDataService.saveRole(userRole);
            role = rolesDataService.findRoleByName("USER");
        }
        user.setRoles(Arrays.asList(role));

        User savedUser = userRepository.save(user);
        User savedFetchedUser = userRepository.findById(savedUser.getId());
        return savedFetchedUser;
    }

    @Override
    public User saveUser(User user, String roleName) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role role = rolesDataService.findRoleByName(roleName);
        if (role == null) {
            Role userRole = new Role();
            userRole.setRole("USER");
            rolesDataService.saveRole(userRole);
            role = rolesDataService.findRoleByName("USER");
        }
        user.setRoles(Arrays.asList(role));

        User savedUser = userRepository.save(user);

        logger.info("Saved user:::", user);

        return savedUser;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else
            return null;
    }

    @Override
    public User findUserByUsername(String userName) {
        User user = userRepository.findByUsername(userName);

        return user;
    }

    private List<GrantedAuthority> getUserAuthority(List<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
    }


    @Override
    public List<User> findUsersForAssigneeChange(String searchInput) {
        List<User> usersFound = userRepository.findByUsernameContainingIgnoreCase(searchInput);


        return usersFound;
    }

    @Override
    public User findUserById(Long id) {
        User user = userRepository.findById(id);

        return user;
    }


}
