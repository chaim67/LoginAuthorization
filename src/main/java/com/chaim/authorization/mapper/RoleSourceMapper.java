package com.chaim.authorization.mapper;

import com.chaim.authorization.model.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleSourceMapper {
    Boolean insert(@Param("resourceId") Integer resourceId, @Param("roleId")Integer roleId);

    List<RoleResource> selectByRoleId(@Param("roleId")Integer roleId);
}
