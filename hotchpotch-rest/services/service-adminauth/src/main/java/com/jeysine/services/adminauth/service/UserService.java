package com.jeysine.services.adminauth.service;

import com.jeysine.services.adminauth.entity.User;
import com.jeysine.services.adminauth.entity.UserDto;
import com.jeysine.services.common.exception.GlassesException;
import com.jeysine.services.common.service.BaseService;

/**
 * @author jxyao
 * @date 2018-09-30
 */
public interface UserService extends BaseService<User, UserDto> {


    /**
     * 通过admin操作注册
     * @param user
     * @throws GlassesException
     */
    void registerByAdmin(UserDto user) throws GlassesException;

    /**
     * 冻结用户
     * @param account
     */
    void frozenUser(String account);

    /**
     * 激活用户
     * @param account
     */
    void activeUser(String account);

}
