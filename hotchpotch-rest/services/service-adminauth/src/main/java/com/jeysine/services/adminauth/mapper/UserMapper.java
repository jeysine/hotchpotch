package com.jeysine.services.adminauth.mapper;

import com.jeysine.services.adminauth.entity.User;
import com.jeysine.services.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User, User> {
}
