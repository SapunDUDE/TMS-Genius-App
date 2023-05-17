package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.model.domain.Role;
import com.genius.tms_c61_genius.repository.RoleRepository;
import com.genius.tms_c61_genius.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String createRole(String roleName) {
        return roleRepository.save(Role.builder()
                .roleName(roleName)
                .build()).getRoleName();
    }

    @Override
    public String updateRole(Integer roleId,String roleName) {
        Role updatedRole = roleRepository.getRoleById(roleId);
        updatedRole.setRoleName(roleName);
        return roleRepository.save(updatedRole).getRoleName();
    }

    @Override
    public void deleteRole(Integer roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public String getRole(Integer roleId) {
        return roleRepository.getRoleById(roleId).getRoleName();
    }
}
