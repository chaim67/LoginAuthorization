package com.chaim.authorization.model.entity;

import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
}
