package com.jeysine.services.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import com.jeysine.services.user.dto.UserDto;
import com.jeysine.services.user.entity.User;
import com.jeysine.services.user.mapper.UserMapper;
import com.jeysine.services.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author yaojx
 * @date 2018-10-8
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper glassesUserMapper;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    public UserServiceImpl(UserMapper glassesUserMapper) {
        this.glassesUserMapper = glassesUserMapper;
        super.setMapper(glassesUserMapper);
    }

    @Override
    public PageInfo<UserDto> findByConditionPage(UserDto qm, int pageSize, int pageNum) throws ParseException {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDto> list = findByCondition(qm);
        PageInfo<UserDto> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public void resetPassword(String username, String password, String category) {
        glassesUserMapper.resetPassword(username, password, category);
    }
}
