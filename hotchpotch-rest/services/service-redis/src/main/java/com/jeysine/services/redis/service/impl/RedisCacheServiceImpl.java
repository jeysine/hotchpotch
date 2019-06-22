package com.jeysine.services.redis.service.impl;

import com.jeysine.services.redis.service.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author yaojx
 * @date 2018-12-06
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    @Override
    public Boolean setValueWithExpire(String key, String value, long expire) {
        try {
            Object result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                Boolean px = connection.set(key.getBytes(), value.getBytes(), Expiration.from(expire, TimeUnit.MILLISECONDS), RedisStringCommands.SetOption.ifAbsent());
                return px;
            });
            return result != null;
        } catch (Exception e) {
            logger.error("set redis lock error: {}", e);
        }
        return false;
    }

    @Override
    public Object get(String key) {
        try {
            Object result = redisTemplate.execute((RedisCallback<String>) connection -> {
                byte[] px = connection.get(key.getBytes());
                if (px != null) {
                    return new String(px);
                }
                return null;
            });
            return result;
        } catch (Exception e) {
            logger.error("get redis value error: {}", e);
        }
        return "";
    }

    @Override
    public Boolean isLeader(String key, String value, long expire) {
        // 防止线程或进程第一次抢锁成功, 第二次时无法持锁
        Object redisLock = get(key);
        if (redisLock != null) {
            return redisLock.toString().equals(value);
        }

        return setValueWithExpire(key, value, expire);
    }
}

