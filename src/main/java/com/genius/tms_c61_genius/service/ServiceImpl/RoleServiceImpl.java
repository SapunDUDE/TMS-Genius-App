package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
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
        if(roleRepository.existsRoleByRoleName(roleName))
            throw new BadDataException("role with such name is already exist");
        return roleRepository.save(Role.builder()
                .roleName(roleName)
                .build()).getRoleName();
    }

    @Override
    public String updateRole(Integer roleId,String roleName) {
        if(!roleRepository.existsRoleById(roleId))
            throw new NotFoundException("role not found");
        Role updatedRole = roleRepository.getRoleById(roleId);
        updatedRole.setRoleName(roleName);
        return roleRepository.save(updatedRole).getRoleName();
    }

    @Override
    public void deleteRole(Integer roleId) {
        if(!roleRepository.existsRoleById(roleId))
            throw new NotFoundException("role not found");
        roleRepository.deleteById(roleId);
    }

    @Override
    public String getRole(Integer roleId) {
        if(!roleRepository.existsRoleById(roleId))
            throw new NotFoundException("role not found");
        return roleRepository.getRoleById(roleId).getRoleName();
    }
}
