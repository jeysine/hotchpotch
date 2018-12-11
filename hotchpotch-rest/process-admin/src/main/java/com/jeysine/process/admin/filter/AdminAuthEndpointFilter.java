package com.jeysine.process.admin.filter;

/**
 * 权限过滤器类
 *
 * @author jxyao
 * @create 2018-05-24 19:19
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.process.common.constants.SystemConstants;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.common.entity.CommonContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AdminAuthEndpointFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(AdminAuthEndpointFilter.class);

    private static final ObjectMapper mapper = new ObjectMapper();

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

        String userName = (String) ((HttpServletRequest) req).getSession().getAttribute(SystemConstants.USER_SESSION_ACCOUNT);
        String userId = (String) ((HttpServletRequest) req).getSession().getAttribute(SystemConstants.USER_SESSION_ID);
        String roleId = (String) ((HttpServletRequest) req).getSession().getAttribute(SystemConstants.USER_SESSION_ROLE_ID);
        if (!StringUtils.isEmpty(userName)) {
            CommonContextHolder.setUserName(userName);
            CommonContextHolder.setUserId(userId);
            CommonContextHolder.setRoleId(roleId);
            chain.doFilter(request, response);
            CommonContextHolder.clear();
        } else {
            ResponseVO result = ResponseVO.noLogin();
            response.getWriter().write(mapper.writeValueAsString(result));
        }
    }
}
