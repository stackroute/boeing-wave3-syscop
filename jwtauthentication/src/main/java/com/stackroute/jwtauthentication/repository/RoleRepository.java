package com.stackroute.jwtauthentication.repository;

import java.util.Optional;

import com.stackroute.jwtauthentication.model.Role;
import com.stackroute.jwtauthentication.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}