package com.genius.tms_c61_genius.service;

public interface RoleService {
    String createRole(String roleName);
    String updateRole(Integer roleId,String roleName);
    void deleteRole(Integer roleId);
    String getRole(Integer roleId);
}
