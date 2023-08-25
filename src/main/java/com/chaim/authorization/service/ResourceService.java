package com.chaim.authorization.service;

import com.chaim.authorization.model.entity.Resource;

import java.util.List;

public interface ResourceService {

    /**
     * 保存资源信息
     *
     * @param resource 待保存资源
     * @return 资源id
     */
    Integer save(Resource resource);
    /**
     * 根据资源id获取资源详情
     * @param id id
     * @return 对应的资源信息
     */
    Resource findById(Integer id);
    /**
     * 查询所有的资源信息
     * @return 资源列表
     */
    List<Resource> findAll();
    /**
     * 删除资源
     * @param id id
     */
    void delete(Integer id);
}
