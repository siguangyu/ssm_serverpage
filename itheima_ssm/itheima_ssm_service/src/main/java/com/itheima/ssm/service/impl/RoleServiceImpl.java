package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/16$ 15:04$
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao dao;

    @Override
    public List<Role> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(String roleName,String roleDesc) {
        dao.save(roleName,roleDesc);
    }

    @Override
    public List<Role> findOtherAll(String id) {
        return dao.findOtherAll(id);
    }

    @Override
    public Role findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String id) {
        return dao.findOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            dao.addPermissionToRole(roleId,permissionId);
        }

    }

}
