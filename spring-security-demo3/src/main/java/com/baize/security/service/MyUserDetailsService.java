package com.baize.security.service;

import com.baize.security.entity.Role;
import com.baize.security.entity.User;
import com.baize.security.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetailsService的实现类，用于在程序中引入一个自定义的AuthenticationProvider，实现数据库访问模式的验证
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //从数据库中取出用户信息
        User user = userService.findByName(s);
        //判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("数据库中无此用户！");
        }
        //添加权限
        List<UserRole> userRoles = userRoleService.listByUserId(user.getUserId());
        for (UserRole userRole : userRoles) {
            Role role = roleService.findById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
