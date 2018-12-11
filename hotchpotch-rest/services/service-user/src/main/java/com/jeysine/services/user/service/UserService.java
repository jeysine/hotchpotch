package com.jeysine.services.user.service;

import com.jeysine.services.common.service.BaseService;
import com.jeysine.services.user.dto.UserDto;
import com.jeysine.services.user.entity.User;

/**
 * @author yaojx
 * @date 2018-10-08
 */
public interface UserService extends BaseService<User, UserDto> {

    /**
     * 重置密碼
     * @param username
     * @param password
     * @param category
     */
    void resetPassword(String username, String password, String category);


}
