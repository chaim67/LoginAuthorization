package com.chaim.authorization.config;

import com.chaim.authorization.config.interceptor.AuthenticationInterceptor;
import com.chaim.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author chaim67
 * @version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /** 不验证权限的url */
    private static final String exclusions = "/login,/oauth/token,/**/*.html,/**/*.js,/**/*.css,/**/*.jpg,/**/*.png,/**/*.ttf,/**/*.woff,/**/*.woff2";

    @Autowired
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration =
                registry.addInterceptor(new AuthenticationInterceptor(userService));
        String[] arrays = exclusions.split(",");
        if (arrays.length > 0) {
            interceptorRegistration.excludePathPatterns(Arrays.asList(arrays));
        }
    }
}
