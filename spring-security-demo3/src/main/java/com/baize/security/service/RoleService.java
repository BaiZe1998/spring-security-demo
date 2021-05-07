package com.baize.security.service;

import com.baize.security.entity.Role;
import com.baize.security.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public Role findById(long id) {
        return roleMapper.findById(id);
    }
}
