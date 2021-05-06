package com.baize.security.service;

import com.baize.security.entity.User;
import com.baize.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
