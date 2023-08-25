package com.chaim.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chaim67
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class LoginUser {
    private Integer userId;
    private String phone;
}
