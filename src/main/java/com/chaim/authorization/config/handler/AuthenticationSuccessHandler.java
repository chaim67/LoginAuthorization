package com.chaim.authorization.config.handler;

import com.chaim.authorization.service.UserService;
import com.chaim.authorization.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.chaim.authorization.constant.Constants.CLIENT_ID;
import static com.chaim.authorization.constant.Constants.CLIENT_SECRET;

/**
 * @author chaim67
 * @version 1.0
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ClientDet clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String clientId = request.getParameter(CLIENT_ID);
        String clientSecret = request.getParameter(CLIENT_SECRET);
        log.info("userName:{}, password:{}, clientId:{}, clientSecret:{}",
                StringEscapeUtils.escapeJava(username), StringEscapeUtils.escapeJava(password),
                StringEscapeUtils.escapeJava(clientId), StringEscapeUtils.escapeJava(clientSecret));
        //获取 ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null){
            throw new UnapprovedClientAuthenticationException("clientId 不存在"+clientId);
            //判断  方言  是否一致
        }else if (!StringUtils.equals(clientDetails.getClientSecret(),clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret 不匹配"+clientId);
        }
        //密码授权 模式，组建 authentication
        TokenRequest tokenRequest = new TokenRequest(new HashMap<String,String>(),clientId,clientDetails.getScope(),"password");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request,authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        log.info("token:"+ JSONUtil.toJSON(token));
        //根据用户名和token先查询是否已经存在
        Integer count = userService.countLogin(username,token.getValue());
        if(null != count && count > 0){
            log.info("用户："+username+"-"+" token:"+token.getValue()+"已经存在");
        }else{
            //根据用户名称和tokenId保存登录信息
            userService.saveLogin(username,token.getValue());
        }
        //登录次数 +1
        userService.addLogin(username);
        //将 authention 信息打包成json格式返回
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(JSONUtil.toJSON(token));
        response.sendRedirect("/main.html?tokenId="+token.getValue());
    }
}
