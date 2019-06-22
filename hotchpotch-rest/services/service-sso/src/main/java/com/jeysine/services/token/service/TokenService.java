package com.jeysine.services.token.service;

import com.jeysine.services.token.entity.UserToken;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author yaojx
 * @date 2018-11-27
 */
public interface TokenService {
    /**
     * 生成token
     * @param username
     * @param password
     * @return
     */
    String getToken(String username, String password);

    /**
     * 解析token信息
     * @param token
     * @return
     * @throws ExpiredJwtException
     */
    UserToken decodeToken(String token) throws ExpiredJwtException;

    /**
     * 更新token
     * @param token
     * @return
     * @throws Exception
     */
    String updateToken(String token) throws Exception;
}
