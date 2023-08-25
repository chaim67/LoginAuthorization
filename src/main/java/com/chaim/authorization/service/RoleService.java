package com.chaim.authorization.service;

import com.chaim.authorization.model.entity.Resource;
import com.chaim.authorization.model.entity.Role;

import java.util.List;

/**
 * @author chaim67
 * @version 1.0
 */
public interface RoleService {

    /**
     * 保存角色信息
     *
     * @param role 角色信息
     * @return 保存结果
     */
    Boolean save(Role role);

    /**
     * 根据角色id获取该角色详情
     *
     * @param roleId 角色id
     * @return 角色详情
     */
    Role findById(Integer roleId);

    /**
     * 查询全部角色信息
     *
     * @return 全部角色信息
     */
    List<Role> findAll();

    /**
     * 查询角色数量
     *
     * @return 角色数量
     */
    Integer getRoleCount();

    /**
     * 根据id 删除角色
     *
     * @param roleId 待删除角色id
     */
    void delete(Integer roleId);

    /**
     * 将资源授权给角色
     *
     * @param resourceId 资源id
     * @param roleId 角色id
     * @return 授权结果
     */
    Boolean grantResource(Integer resourceId, Integer roleId);

    /**
     * 根据 roleId 获取该角色对应的所有资源
     *
     * @param roleId 角色id
     * @return 角色的资源列表
     */
    List<Resource> getResourcesById(Integer roleId);


}
