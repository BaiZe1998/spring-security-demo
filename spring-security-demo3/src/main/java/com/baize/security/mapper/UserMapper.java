package com.baize.security.mapper;

import com.baize.security.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User findByName(String username);
}
