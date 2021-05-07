package com.baize.security.mapper;

import com.baize.security.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    Role findById(long id);
}
