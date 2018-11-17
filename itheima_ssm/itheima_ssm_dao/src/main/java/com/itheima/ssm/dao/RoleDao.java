package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in (select roleid from users_role where userId=#{userId})")
    @Results({
            @Result(property ="id",id =true,column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.PermissionDao.findByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(rolename,roledesc) values(#{param1},#{param2})")
    void save(String roleName,String roleDesc);

    @Select("select * from role where id not in (select roleid from users_role where userId=#{userID})")
    List<Role> findOtherAll(String userID);

    @Select("select * from role where id=#{id}")
    Role findById(String id);

    @Select("select * from permission where id not in (select permissionid from ROLE_PERMISSION where roleid=#{id})")
    List<Permission> findOtherPermission(String id);

    @Insert("insert into ROLE_PERMISSION (roleId,permissionId) values(#{param1},#{param2})")
    void addPermissionToRole(String roleId, String permissionId);
}
