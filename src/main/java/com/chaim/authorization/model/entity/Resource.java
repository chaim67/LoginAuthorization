package com.chaim.authorization.model.entity;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class Resource {
    private Integer id;
    private String code;
    private String name;
    private String url;
    private String method;
}
