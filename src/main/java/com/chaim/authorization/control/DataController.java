package com.chaim.authorization.control;

import com.chaim.authorization.exception.BusinessException;
import com.chaim.authorization.model.bo.ResourceForm;
import com.chaim.authorization.model.entity.Resource;
import com.chaim.authorization.model.vo.ResourceVo;
import com.chaim.authorization.service.ResourceService;
import com.chaim.authorization.util.BeanCopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

import static com.chaim.authorization.util.BeanCopyUtil.copyList;

/**
 * @author chaim67
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(value = "/data")
public class DataController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/resource")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveResource(@RequestBody ResourceForm resourceForm) {
        Resource resource = BeanCopyUtil.copy(resourceForm, Resource.class);
        return resourceService.save(resource);
    }

    @GetMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResourceVo findResourceById(@PathVariable("id") @Min(value=1,message="id不能小于1")Integer id) {
        Resource resource = resourceService.findById(id);
        if(null == resource){
            throw new BusinessException("资源信息不存在");
        }
        log.info("resource:{}", resource);
        return BeanCopyUtil.copy(resource,ResourceVo.class);
    }

    @GetMapping("/resource")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ResourceVo> findAllResources() {
        List<Resource> resources = resourceService.findAll();
        return copyList(resources,ResourceVo.class);
    }

    @DeleteMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteResource(@PathVariable("id") @Min(value=1,message="id不能小于1") Integer id) {
        resourceService.delete(id);
        return true;
    }
}
