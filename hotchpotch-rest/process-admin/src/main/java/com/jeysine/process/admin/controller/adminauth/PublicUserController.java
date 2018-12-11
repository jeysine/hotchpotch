package com.jeysine.process.admin.controller.adminauth;


import com.jeysine.process.common.constants.SystemConstants;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.adminauth.entity.User;
import com.jeysine.services.adminauth.entity.UserDto;
import com.jeysine.services.adminauth.service.UserService;
import com.jeysine.services.common.constants.ApiCode;
import com.jeysine.services.common.constants.CommonConstants;
import com.jeysine.services.common.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@RestController
@RequestMapping("/public/user")
public class PublicUserController {

    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(PublicUserController.class);

    @Autowired
    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @param request http请求
     * @return 用户
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVO login(@RequestBody UserDto user, HttpServletRequest request) {
        UserDto checkUser = userService.findOneByCondition(user);
        if (checkUser == null) {
            return ResponseVO.error(ApiCode.USER_ACCOUNT_PASSWORD_ERROR.getStatus(), ApiCode.USER_ACCOUNT_PASSWORD_ERROR.getMessage());
        }
        if (CommonConstants.AdminUserStatusEnum.ACTIVE.getValue().equals(checkUser.getStatus())
                && Md5Util.MD5Encode(user.getPassword(), SystemConstants.SYSTEM_ENCODING).equals(checkUser.getPassword())) {
            request.getSession().setAttribute(SystemConstants.USER_SESSION_ID, checkUser.getId());
            request.getSession().setAttribute(SystemConstants.USER_SESSION_ACCOUNT, checkUser.getName());
            request.getSession().setAttribute(SystemConstants.USER_SESSION_ROLE_ID, checkUser.getRoleId());
            return new ResponseVO<User>().success(user);
        } else if (CommonConstants.AdminUserStatusEnum.FROZEN.getValue().equals(checkUser.getStatus())) {
            return ResponseVO.error(ApiCode.USER_FROZEN.getStatus(), ApiCode.USER_FROZEN.getMessage());
        } else if (CommonConstants.AdminUserStatusEnum.NOT_ACTIVE.getValue().equals(checkUser.getStatus())) {
            return ResponseVO.error(ApiCode.USER_NOT_ACTIVE.getStatus(), ApiCode.USER_NOT_ACTIVE.getMessage());
        }

        return ResponseVO.error(ApiCode.UNKNOWN_ERROR.getStatus(), ApiCode.UNKNOWN_ERROR.getMessage());
    }
}