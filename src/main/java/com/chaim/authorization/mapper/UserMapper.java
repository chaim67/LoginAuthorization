package com.chaim.authorization.mapper;

import com.chaim.authorization.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer save(User user);

    int update(User user);

    User findById(@Param("id") Integer id);

    User findByUserName(@Param("userName") String userName);

    List<User> findByPage();

    void delete(@Param("id") Integer id);

    Long count();

    Long countByUserName(@Param("userName") String userName);
}
