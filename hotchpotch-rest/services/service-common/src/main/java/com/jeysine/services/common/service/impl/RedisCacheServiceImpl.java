package com.jeysine.services.common.service.impl;

import com.jeysine.services.common.service.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCommands;

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
    public Boolean setLock(String key, String value, long expire) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                /**
                 * 当该键不存在的时候设置成功, 若存在则返回null,
                 */
                return commands.set(key, value, "NX", "PX", expire);
            };
            Object result = redisTemplate.execute(callback);

            return result != null;
        } catch (Exception e) {
            logger.error("set redis lock error: {}", e);
        }
        return false;
    }

    @Override
    public Object get(String key) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.get(key);
            };
            Object result = redisTemplate.execute(callback);
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

        return setLock(key, value, expire);
    }
}
