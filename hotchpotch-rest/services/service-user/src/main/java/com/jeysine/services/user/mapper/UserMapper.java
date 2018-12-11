package com.jeysine.services.user.mapper;

import com.jeysine.services.common.mapper.BaseMapper;
import com.jeysine.services.user.dto.UserDto;
import com.jeysine.services.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {
    /**
     * 重置密码
     * @param username
     * @param password
     * @param category
     */
    void resetPassword(@Param("username") String username, @Param("password") String password, @Param("category") String category);


}
