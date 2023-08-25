package com.chaim.authorization.config.handler;

import com.chaim.authorization.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chaim67
 * @version 1.0
 */
@Slf4j
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        log.info("登陆失败");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(JSONUtil.toJSON("登录认证失败,请重新登录"));
    }
}
