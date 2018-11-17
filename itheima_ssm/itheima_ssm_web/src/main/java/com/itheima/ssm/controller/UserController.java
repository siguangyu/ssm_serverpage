package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.RoleService;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/15$ 16:41$
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAllUser")
/*    @RolesAllowed("ROLE_ADMIN")*/
// ROLE_  可以省略
    @RolesAllowed("ADMIN")
    public ModelAndView findAllUser(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                    @RequestParam(name = "size",required = true,defaultValue = "5")Integer size){
        ModelAndView mv=new ModelAndView();
        List<UserInfo> allUser = userService.findAllUser(page,size);
        PageInfo pageInfo=new PageInfo(allUser);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveUser(UserInfo userInfo){
        userService.saveUser(userInfo);
        return "redirect:findAllUser";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv =new ModelAndView();
       UserInfo user= userService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")

    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> roleList = roleService.findOtherAll(id);
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
}
