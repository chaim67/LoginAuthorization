package com.chaim.authorization.model.bo;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class ResourceForm {
    private Integer id;
    private String name;
    private String url;
    private String method;
}
