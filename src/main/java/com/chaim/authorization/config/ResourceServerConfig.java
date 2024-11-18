package com.chaim.authorization.config;

import com.chaim.authorization.config.handler.AuthenticationFailHandler;
import com.chaim.authorization.config.handler.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author chaim67
 * @version 1.0
 */
//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailHandler failHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*.js","/**/*.html","/**/*.css",
                        "/oauth/**",
                        "/**/*.jpg","/**/*.png","/**/*.ttf","/**/*.woff",
                        "/**/*.woff2").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()//允许用户进行基于表单的认证
                .loginPage("/login.html")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .headers().frameOptions().disable()
                .and()
                // 暂时禁用CSRF，否则无法提交登录表单
                .csrf().disable();
    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }
}
