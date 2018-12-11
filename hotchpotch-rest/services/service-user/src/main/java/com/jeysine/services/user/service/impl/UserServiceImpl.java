package com.jeysine.services.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeysine.services.common.constants.CommonConstants;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import com.jeysine.services.user.dto.UserDto;
import com.jeysine.services.user.entity.User;
import com.jeysine.services.user.mapper.UserMapper;
import com.jeysine.services.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yaojx
 * @date 2018-10-8
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper glassesUserMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    public UserServiceImpl(UserMapper glassesUserMapper, RedisTemplate<String, Object> redisTemplate) {
        this.glassesUserMapper = glassesUserMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public PageInfo<UserDto> findByConditionPage(UserDto qm, int pageSize, int pageNum) throws ParseException {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        PageHelper.startPage(pageNum, pageSize);
        List<UserDto> list = findByCondition(qm);
        list = list.stream().map(e -> {
            Object status = hashOperations.get(CommonConstants.REDIS_KEY_CUSTOM_STATUS, e.getUsername());

            return e;
        }).collect(Collectors.toList());
        PageInfo<UserDto> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public void resetPassword(String username, String password, String category) {
        glassesUserMapper.resetPassword(username, password, category);
    }
}
