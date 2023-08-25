package com.chaim.authorization.service.impl;

import com.chaim.authorization.mapper.ResourceMapper;
import com.chaim.authorization.mapper.RoleMapper;
import com.chaim.authorization.mapper.RoleSourceMapper;
import com.chaim.authorization.model.entity.Resource;
import com.chaim.authorization.model.entity.Role;
import com.chaim.authorization.model.entity.RoleResource;
import com.chaim.authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chaim67
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleSourceMapper roleSourceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Boolean save(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Integer getRoleCount() {
        return roleMapper.count();
    }

    @Override
    public void delete(Integer roleId) {
        roleMapper.deleteById(roleId);
    }

    @Override
    public Boolean grantResource(Integer resourceId, Integer roleId) {
        return roleSourceMapper.insert(resourceId, roleId);
    }

    @Override
    public List<Resource> getResourcesById(Integer roleId) {
        List<RoleResource> roleResources = roleSourceMapper.selectByRoleId(roleId);
        Set<Integer> resourceIdSet = roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
        return resourceMapper.selectAll().stream()
                .filter(resource -> resourceIdSet.contains(resource.getId()))
                .collect(Collectors.toList());
    }
}
