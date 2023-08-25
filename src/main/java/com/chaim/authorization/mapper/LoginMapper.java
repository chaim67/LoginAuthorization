package com.chaim.authorization.mapper;

import com.chaim.authorization.model.entity.Login;
import com.chaim.authorization.model.entity.LoginCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {

    /**
     * 保存用户和tokenId的关系
     *
     * @param userName 用户名
     * @param tokenId tokenId
     */
    void saveLogin(@Param("userName")String userName,@Param("tokenId") String tokenId);

    /**
     * 根据用户名称和tokenId查询记录条数
     *
     * @param userName 用户名
     * @param tokenId tokenId
     * @return 记录条数
     */
    Integer countLogin(@Param("userName") String userName,@Param("tokenId") String tokenId);

    /**
     * 根据token获取用户名称
     * @param tokenId token id
     * @return 用户名称
     */
    String findUserNameByTokenId(@Param("tokenId") String tokenId);

    /**
     * 根据用户名称查询登录次数
     *@param userName 用户名
     * @return 用户登录次数
     */
    Integer countLoginByUserName(@Param("userName") String userName);

    /**
     * 首次登录插入登录记录
     *
     * @param userName 用户名
     */
    void firstLogin(@Param("userName") String userName);

    /**
     * 登录次数+1
     *
     * @param userName 用户名
     */
    void addLogin(@Param("userName") String userName);

    /**
     * 查询所有登录记录
     *
     * @return 登录记录
     */
    List<Login> findAll();

}

