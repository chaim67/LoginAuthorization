package com.chaim.authorization.service;

import com.chaim.authorization.model.entity.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 只做用户名和密码登录认证
     *
     * @param userName 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    /**
     * 保存用户
     *
     * @param user 用户信息
     * @return 保存结果
     */
    Integer save(User user);

    /**
     * 根据用户名称查询该名称对应的用户条数
     *
     * @param userName 用户名
     * @return 该用户名数量
     */
    long countByUserName(String userName);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User findByUserName(String userName);

    /**
     * 根据用户 ID 查询用户信息
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    User findById(Integer id);

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<User> findByPage();

    /**
     * 查询用户条数
     *
     * @return 用户数量
     */
    long count();

    /**
     * 删除用户
     *
     * @param userId 用户 ID
     */
    void delete(Integer userId);

    /**
     * 给用户授权角色
     * @param userId 用户 ID
     * @param roleIds 角色id列表
     */
    void grantRoleToUser(Integer userId, List<Integer> roleIds);

    /**
     * 根据用户 ID 查询该用户所拥有的角色信息
     *
     * @param userId 用户 ID
     * @return 角色列表
     */
    List<Role> findRoleByUserId(Integer userId);

    /**
     * 根据用户 ID 查询该用户所有的资源信息
     *
     * @param userId 用户 ID
     * @return 资源信息列表
     */
    List<Resource> findResourceByUserId(Integer userId);

    /**
     * 根据用户访问的 url 和 tokenId 判断该用户是否拥有访问该 url 的资格
     *
     * @param url 资源url
     * @param tokenId tokenId
     * @param method 方法
     * @return 是否具有访问资格
     */
    boolean checkLoginUser(String url, String tokenId, String method);

    /**
     * 保存用户和 tokenId
     *
     * @param userName 用户名
     * @param tokenId tokenId
     */
    void saveLogin(String userName, String tokenId);

    /**
     * 用户登录次数 +1
     *
     * @param userName 用户名
     */
    void addLogin(String userName);

    /**
     * 查询该用户该 token 条数
     *
     * @param userName 用户名
     * @param tokenId tokenId
     * @return token条数
     */
    Integer countLogin(String userName, String tokenId);

    /**
     * 获取所有的登录信息
     *
     * @return 登录信息
     */
    List<Login> findAllLoginInfo();
}
