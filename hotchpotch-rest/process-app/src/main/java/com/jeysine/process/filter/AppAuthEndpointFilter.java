package com.jeysine.process.filter;

/**
 * 权限过滤器类
 *
 * @author jxyao
 * @create 2018-05-24 19:19
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.common.constants.CommonConstants;
import com.jeysine.services.common.entity.CommonContextHolder;
import com.jeysine.services.token.entity.UserToken;
import com.jeysine.services.token.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台用户登录信息注入过滤器
 * @author yaojx.
 */
public class AppAuthEndpointFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(AppAuthEndpointFilter.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //禁用頁面緩存, 防止用戶註銷后可通過頁面緩存繞過登陸
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma","no-cache");

        String token = ((HttpServletRequest) req).getHeader(CommonConstants.APP_TOKEN);;
        if (!StringUtils.isEmpty(token)) {
            try {
                UserToken userToken = tokenService.decodeToken(token);
                CommonContextHolder.setUserName(userToken.getUsername());
                chain.doFilter(request, response);
                response.setHeader(CommonConstants.APP_TOKEN, tokenService.updateToken(token));
                CommonContextHolder.clear();
            } catch (ExpiredJwtException e) {
                response.getWriter().write(mapper.writeValueAsString(ResponseVO.noLogin()));
            } catch (Exception e) {
                logger.error("error: {}", e);
            }
        } else {
            response.getWriter().write(mapper.writeValueAsString(ResponseVO.noLogin()));
        }
    }
}
