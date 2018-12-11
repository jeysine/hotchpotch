package com.jeysine.services.adminauth.service.impl;

import com.jeysine.services.adminauth.entity.UserRole;
import com.jeysine.services.adminauth.mapper.UserRoleMapper;
import com.jeysine.services.adminauth.service.UserRoleService;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jxyao
 * @date 2018-5-18
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRole> implements UserRoleService {

    private final UserRoleMapper mapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper mapper) {
        this.mapper = mapper;
        super.setMapper(mapper);
    }
}
