/** 默认的oauth客户端id和secret  默认的为login_test 123456 */
INSERT INTO `oauth_client_details`
VALUES ('login_test', NULL, '$2a$10$af9lXckfuaHRCO7vEOJcgOD9kXvZ5zXD4g/I6gFfd1mHfOifsaHIu', 'all', 'password', NULL,
        NULL, NULL, NULL, NULL, NULL);

INSERT INTO sys_user (id, user_name, password, phone)
VALUES (1, 'ADMIN', 'ADMIN', NULL);