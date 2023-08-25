package com.chaim.authorization.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    void deleteByUserId(@Param("userId") Integer userId);

    void insertRoles(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    List<Integer> findRoleIdsByUserId(@Param("userId") Integer userId);
}
