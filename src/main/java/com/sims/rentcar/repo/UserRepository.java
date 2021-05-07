package com.sims.rentcar.repo;

import com.sims.rentcar.model.Role;
import com.sims.rentcar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndRolesIn(String username, List<Role> roles);
}

