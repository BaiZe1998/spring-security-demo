package com.baize.security.mapper;

import com.baize.security.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    List<UserRole> listByUserId(long userId);
}
