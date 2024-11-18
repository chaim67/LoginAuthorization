package com.chaim.authorization.config.interceptor;

import com.chaim.authorization.exception.BusinessException;
import com.chaim.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chaim67
 * @version 1.0
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "access_token";
    private final UserService userService;

    public AuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/".equals(request.getRequestURI())) {
            return true;
        }
        return userAuth(request);
    }

    /**
     * 实际用户token鉴权
     *
     * @param request http请求
     */
    private boolean userAuth(HttpServletRequest request) {
        String tokenId = request.getParameter(TOKEN_NAME);
        if (StringUtils.isEmpty(tokenId)) {
            throw new BusinessException("您尚未登录,请登录系统！");
        }
        //获取访问的url
        String url = request.getRequestURI();
        String method = request.getMethod().toUpperCase();
        log.info("url:{}", url);
        log.info("method:{}", method);
        return userService.checkLoginUser(url, tokenId, method);
    }
}
