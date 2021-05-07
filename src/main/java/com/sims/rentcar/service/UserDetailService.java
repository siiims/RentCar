package com.sims.rentcar.service;

import com.sims.rentcar.model.Role;
import com.sims.rentcar.model.User;
import com.sims.rentcar.repo.RoleRepository;
import com.sims.rentcar.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean userExists(String username) {
        List<Role> roles = findByAssignedRoles();
        User user = userRepository.findByUsernameAndRolesIn(username, roles);
        return null != user;
    }

    private List<Role> findByAssignedRoles(){
        List<Role> roleList = new ArrayList<Role>();
        Role adminRole = roleRepository.getOne(1l);
        roleList.add(adminRole);
        return roleList;
    }

}
