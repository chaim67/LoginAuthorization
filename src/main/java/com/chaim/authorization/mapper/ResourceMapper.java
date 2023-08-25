package com.chaim.authorization.mapper;

import com.chaim.authorization.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {

    Integer insert(@Param("resource") Resource resource);

    Resource selectById(@Param("id") Integer id);

    List<Resource> selectAll();

    void deleteById(@Param("id") Integer id);
}
