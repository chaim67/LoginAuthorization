package com.chaim.authorization.mapper;

import com.chaim.authorization.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    Boolean insert(@Param("role") Role role);

    Role selectById(@Param("roleId") Integer roleId);

    List<Role> selectAll();

    Integer count();

    void deleteById(@Param("roleId")Integer roleId);
}
