package com.chaim.authorization.service.impl;

import com.chaim.authorization.mapper.ResourceMapper;
import com.chaim.authorization.model.entity.Resource;
import com.chaim.authorization.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chaim67
 * @version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Integer save(Resource resource) {
        return resourceMapper.insert(resource);
    }

    @Override
    public Resource findById(Integer id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }

    @Override
    public void delete(Integer id) {
        resourceMapper.deleteById(id);
    }
}
