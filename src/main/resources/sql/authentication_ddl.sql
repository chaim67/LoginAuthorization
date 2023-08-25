/** 资源表 */
CREATE TABLE IF NOT EXISTS `sys_resource`
(
    `id`     INT(11) NOT NULL AUTO_INCREMENT,
    `code`   VARCHAR(50) DEFAULT NULL,
    `name`   VARCHAR(50) DEFAULT NULL COMMENT '资源名称',
    `url`    VARCHAR(50) DEFAULT NULL COMMENT '资源url',
    `method` VARCHAR(20) DEFAULT NULL COMMENT '请求方式',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='资源表';

/** 角色表 */
CREATE TABLE IF NOT EXISTS `sys_role`
(
    `id`   INT(11) NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(50) DEFAULT NULL,
    `name` VARCHAR(50) DEFAULT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='角色表';

/** 用户表 */
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`        INT(11) NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(50) DEFAULT NULL COMMENT '用户名称',
    `password`  VARCHAR(50) DEFAULT NULL COMMENT '密码',
    `phone`     VARCHAR(11) DEFAULT NULL COMMENT '手机号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';

/** 角色-资源表 */
CREATE TABLE IF NOT EXISTS `sys_role_resource`
(
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `role_id`     INT(11) DEFAULT NULL COMMENT '角色id',
    `resource_id` INT(11) DEFAULT NULL COMMENT '资源id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='角色-资源表';

/** 用户-角色表 */
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    `id`      INT(11) NOT NULL AUTO_INCREMENT,
    `role_id` INT(11) DEFAULT NULL COMMENT '角色id',
    `user_id` INT(11) DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户-角色表';

/** 登录信息统计表 */
CREATE TABLE IF NOT EXISTS `sys_login_count`
(
    `id`        INT(11) NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(50) DEFAULT NULL COMMENT '用户名称',
    `count`     INT(11)     DEFAULT 0 COMMENT '登录次数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户登录信息统计表';

/** 用户tokenId表 */
CREATE TABLE IF NOT EXISTS `sys_login`
(
    `id`        INT(11) NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(50) DEFAULT NULL COMMENT '用户名称',
    `token_id`  VARCHAR(50) DEFAULT NULL COMMENT 'tokenId',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户token信息表';

/** OAUTH相关数据库表 */

DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
    `token_id`          VARCHAR(256) DEFAULT NULL,
    `token`             BLOB,
    `authentication_id` VARCHAR(256) NOT NULL,
    `user_name`         VARCHAR(256) DEFAULT NULL,
    `client_id`         VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
    `token_id`          VARCHAR(256) DEFAULT NULL,
    `token`             BLOB,
    `authentication_id` VARCHAR(256) NOT NULL,
    `user_name`         VARCHAR(256) DEFAULT NULL,
    `client_id`         VARCHAR(256) DEFAULT NULL,
    `authentication`    BLOB,
    `refresh_token`     VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               VARCHAR(256) NOT NULL,
    `resource_ids`            VARCHAR(256)  DEFAULT NULL,
    `client_secret`           VARCHAR(256)  DEFAULT NULL,
    `scope`                   VARCHAR(256)  DEFAULT NULL,
    `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
    `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
    `authorities`             VARCHAR(256)  DEFAULT NULL,
    `access_token_validity`   INT(11)       DEFAULT NULL,
    `refresh_token_validity`  INT(11)       DEFAULT NULL,
    `additional_information`  VARCHAR(4096) DEFAULT NULL,
    `autoapprove`             VARCHAR(256)  DEFAULT NULL,
    PRIMARY KEY (`client_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;



