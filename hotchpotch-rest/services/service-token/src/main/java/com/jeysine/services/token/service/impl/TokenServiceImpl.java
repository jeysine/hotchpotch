package com.jeysine.services.token.service.impl;

import com.jeysine.services.token.entity.UserToken;
import com.jeysine.services.token.service.TokenService;
import com.jeysine.services.token.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Service;

/**
 * @author yaojx
 * @date 2018-11-27
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(String username, String password) {
        return JwtUtils.generateToken(username, null, null);
    }
    @Override
    public UserToken decodeToken(String token) throws ExpiredJwtException {
        String username = JwtUtils.verifyToken(token).getId();
        UserToken userToken = new UserToken();
        userToken.setUsername(username);
        return userToken;
    }

    @Override
    public String updateToken(String token) throws Exception {
        return JwtUtils.updateToken(token);
    }
}
