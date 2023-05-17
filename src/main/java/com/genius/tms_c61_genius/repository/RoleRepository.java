package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByRoleName(String roleName);
    Role getRoleById(Integer id);
    void deleteById(Integer id);
}
