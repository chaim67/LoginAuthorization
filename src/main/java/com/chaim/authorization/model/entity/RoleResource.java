package com.chaim.authorization.model.entity;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class RoleResource {
    private Integer id;
    private Integer roleId;
    private Integer resourceId;
}
