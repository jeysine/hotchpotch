package com.jeysine.services.adminauth.service.impl;

import com.jeysine.services.adminauth.entity.Role;
import com.jeysine.services.adminauth.mapper.RoleMapper;
import com.jeysine.services.adminauth.service.RoleService;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jxyao
 * @date 2018-5-18
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Role> implements RoleService {

    private final RoleMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleMapper mapper) {
        this.mapper = mapper;
        super.setMapper(mapper);
    }

}
