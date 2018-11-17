package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(String roleName,String roleDesc);

    List<Role> findOtherAll(String id);

    Role findById(String id);

    List<Permission> findOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionId);
}
