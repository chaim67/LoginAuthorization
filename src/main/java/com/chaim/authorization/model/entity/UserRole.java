package com.chaim.authorization.model.entity;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
