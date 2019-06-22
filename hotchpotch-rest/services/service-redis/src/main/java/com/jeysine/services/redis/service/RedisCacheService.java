package com.jeysine.services.redis.service;

/**
 * @author yaojx
 * @date 2018-12-06
 */
public interface RedisCacheService {
    /**
     * 设置锁, 可用于分布式锁
     * @param key 键
     * @param value 值
     * @param expire 毫秒
     * @return
     */
    Boolean setValueWithExpire(String key, String value, long expire);

    Object get(String key);

    /**
     * 选取leader
     * @param key 设置分布式锁的键
     * @param value 设置分布式锁的值
     * @param expire 设置分布式锁的时效(毫秒)
     * @return
     */
    Boolean isLeader(String key, String value, long expire);
}
