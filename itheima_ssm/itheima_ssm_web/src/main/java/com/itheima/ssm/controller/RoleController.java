package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/16$ 15:00$
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService service;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roleList=service.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(String roleName,String roleDesc){

        service.save(roleName,roleDesc);

        return "redirect:findAll";
    }
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv=new ModelAndView();
        Role role=service.findById(id);
        List<Permission> permissionList=service.findOtherPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name ="roleId") String roleId,
                                      @RequestParam(name = "ids")String[] permissionId){

    service.addPermissionToRole(roleId,permissionId);
        return "redirect:findAll";
    }
}
