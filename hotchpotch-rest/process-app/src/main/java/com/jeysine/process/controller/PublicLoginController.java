package com.jeysine.process.controller;

import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.process.form.LoginForm;
import com.jeysine.process.vo.LoginVO;
import com.jeysine.services.common.constants.ApiCode;
import com.jeysine.services.common.constants.CommonConstants;
import com.jeysine.services.token.service.TokenService;
import com.jeysine.services.user.dto.UserDto;
import com.jeysine.services.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yaojx
 * @date 2018-10-09
 */
@RestController
@RequestMapping("/public")
public class PublicLoginController {

    private final static Logger logger = LoggerFactory.getLogger(PublicLoginController.class);

    private final TokenService tokenService;

    private final UserService userService;

    @Autowired
    public PublicLoginController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }


    @PostMapping("/login/common")
    public ResponseVO login(@RequestBody LoginForm loginForm, HttpServletRequest request) throws Exception{
        if (!checkParams(loginForm)) {
            return ResponseVO.error(ApiCode.PARAM_ERROR.getStatus(),ApiCode.PARAM_ERROR.getMessage());
        }

        UserDto glassesUser = new UserDto();
        BeanUtils.copyProperties(loginForm, glassesUser);
        glassesUser = userService.findOneByCondition(glassesUser);
        if (glassesUser != null){
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstants.SESSION_USERNAME, glassesUser.getUsername());
            session.setAttribute(CommonConstants.SESSION_USER_ID, glassesUser.getId());
            LoginVO loginVO = new LoginVO();
            BeanUtils.copyProperties(glassesUser, loginVO);
            loginVO.setTtTravelToken(tokenService.getToken(glassesUser.getUsername(), glassesUser.getPassword()));
            return new ResponseVO<LoginVO>().success(loginVO);
        } else {
            return ResponseVO.error(ApiCode.PASSWORD_ERROR);
        }
    }



    private Boolean checkParams(LoginForm loginForm) {
        if (StringUtils.isEmpty(loginForm.getUsername())
            || StringUtils.isEmpty(loginForm.getPassword())
            || StringUtils.isEmpty(loginForm.getCategory())) {
            return false;
        }
        return true;
    }

    @PostMapping("/reset/password")
    public ResponseVO resetPassword(@RequestBody LoginForm loginForm, HttpServletRequest request) throws Exception{
        if (!checkParams(loginForm)) {
            return ResponseVO.error(ApiCode.PARAM_ERROR.getStatus(),ApiCode.PARAM_ERROR.getMessage());
        }
        // TODO: 重置密码实现
        return new ResponseVO<String>().success("");
    }
}
