package com.baize.security.controller;

import com.baize.security.entity.Role;
import com.baize.security.entity.User;
import com.baize.security.entity.UserRole;
import com.baize.security.service.RoleService;
import com.baize.security.service.UserRoleService;
import com.baize.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/user")
    public String user(Model model){
        /**
         * SecurityContextHolder.getContext().getAuthentication()获取到了穿过整个过滤器链的Authentication实例
         * Authentication实例内记录了用户的各种信息，包括用户名，密码，权限等
         */
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
        return "user/user";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
        return "admin/admin";
    }

//    @ResponseBody
//    @RequestMapping("getUser/{name}")
//    public String getUser(@PathVariable String name) {
//        User user = userService.findByName(name);
//        return userService.findByName(name).toString();
//    }
//
//    @ResponseBody
//    @RequestMapping("getList/{id}")
//    public String getList(@PathVariable long id) {
//        List<UserRole> userRoles = userRoleService.listByUserId(id);
//        return userRoles.toString();
//    }
//
//    @ResponseBody
//    @RequestMapping("getRole/{id}")
//    public String getRole(@PathVariable long id) {
//        Role role = roleService.findById(id);
//        return role.toString();
//    }
}
