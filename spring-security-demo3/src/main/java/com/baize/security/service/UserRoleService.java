package com.baize.security.service;

import com.baize.security.entity.UserRole;
import com.baize.security.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> listByUserId(long userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
