package com.chaim.authorization.service.impl;

import cn.hutool.core.text.AntPathMatcher;
import com.chaim.authorization.exception.BusinessException;
import com.chaim.authorization.mapper.*;
import com.chaim.authorization.model.entity.*;
import com.chaim.authorization.service.RoleService;
import com.chaim.authorization.service.UserService;
import com.chaim.authorization.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaim67
 * @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("userName:{}", StringEscapeUtils.escapeJava(userName));
        if (StringUtil.isEmpty(userName)) {
            throw new BusinessException("用户名不可为空");
        }
        User user = userMapper.findByUserName(userName);
        if (null == user) {
            throw new BusinessException("用户信息不存在");
        }
        return new org.springframework.security.core.userdetails.User(userName,
                passwordEncoder.encode(user.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }

    @Override
    public Integer save(User user) {
        if (StringUtil.isEmpty(user.getUserName())) {
            throw new BusinessException("用户名为空");
        }
        if (this.countByUserName(user.getUserName()) > 0) {
            throw new BusinessException("用户名称重复");
        }
        if (null == user.getId()) {
            userMapper.save(user);
        } else {
            //修改
            User oldUser = userMapper.findById(user.getId());
            if (null == oldUser) {
                throw new BusinessException("用户信息不存在");
            }
            //当修改的名称和之前的名称不一样的时候，需要验证名称是否重复
            if (!oldUser.getUserName().equals(user.getUserName())) {
                userMapper.update(user);
            }
        }
        log.info("id:{}", user.getId());
        return user.getId();
    }

    @Override
    public long countByUserName(String userName) {
        return userMapper.countByUserName(userName);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findByPage() {
        return userMapper.findByPage();
    }

    @Override
    public long count() {
        return userMapper.count();
    }

    @Override
    public void delete(Integer userId) {
        userMapper.delete(userId);
    }

    @Override
    public void grantRoleToUser(Integer userId, List<Integer> roleIds) {
        //先删除该用户之前的角色
        userRoleMapper.deleteByUserId(userId);
        //保存新角色
        userRoleMapper.insertRoles(userId, roleIds);
    }

    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.findRoleIdsByUserId(userId);
        List<Role> roles = new ArrayList<>();
        for (Integer roleId : roleIds) {
            Role role = roleService.findById(roleId);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public List<Resource> findResourceByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.findRoleIdsByUserId(userId);
        List<Resource> resources = new ArrayList<>();
        for (Integer roleId : roleIds) {
            List<Resource> list = roleService.getResourcesById(roleId);
            for (Resource resource : list) {
                if (!resources.contains(resource)) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    @Override
    public boolean checkLoginUser(String url, String tokenId, String method) {
        log.info("请求地址:{}", StringEscapeUtils.escapeJava(url));
        log.info("请求方式:{}", StringEscapeUtils.escapeJava(method));
        //根据tokenId获取用户名称
        String userName = loginMapper.findUserNameByTokenId(tokenId);
        if (StringUtil.isEmpty(userName)) {
            throw new BusinessException("无效的tokenId");
        }
        //如果是超级管理员，不需要验证权限（管理员账号为，admin 123456）
        if ("admin".equals(userName)) {
            return true;
        }
        //根据用户名称获取该用户信息
        User user = userMapper.findByUserName(userName);
        if (null == user) {
            throw new BusinessException("用户信息不存在");
        }
        //根据用户信息查询该用户所拥有的资源信息
        List<Resource> resources = this.findResourceByUserId(user.getId());
        boolean flag = false;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        //比对url是否在所拥有的资源url中
        for (Resource resource : resources) {
            if (antPathMatcher.match(resource.getUrl(), url) && method.equals(resource.getMethod())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return true;
        } else {
            throw new BusinessException("无权访问");
        }
    }

    @Override
    public void saveLogin(String userName, String tokenId) {
        loginMapper.saveLogin(userName, tokenId);
    }

    @Override
    public void addLogin(String userName) {
        Integer count = loginMapper.countLoginByUserName(userName);
        if (null == count) {
            loginMapper.firstLogin(userName);
        } else {
            loginMapper.addLogin(userName);
        }
    }

    @Override
    public Integer countLogin(String userName, String tokenId) {
        return loginMapper.countLogin(userName, tokenId);
    }

    @Override
    public List<Login> findAllLoginInfo() {
        return loginMapper.findAll();
    }
}
