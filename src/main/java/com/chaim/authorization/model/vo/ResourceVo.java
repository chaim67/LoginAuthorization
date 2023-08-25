package com.chaim.authorization.model.vo;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class ResourceVo {
    private Integer id;
    private String name;
    private String url;
    private String method;
}
