package com.maurevair.auth.repository;

import com.maurevair.auth.model.Roles;
import com.maurevair.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,String> {
    Users findByUserName(String userName);
}
