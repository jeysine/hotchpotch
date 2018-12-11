package com.jeysine.services.adminauth.service.impl;

import com.jeysine.services.adminauth.entity.MenuRole;
import com.jeysine.services.adminauth.mapper.MenuRoleMapper;
import com.jeysine.services.adminauth.service.MenuRoleService;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jxyao
 * @date 2018-5-18
 */
@Service("menuRoleService")
public class MenuRoleServiceImpl extends BaseServiceImpl<MenuRole, MenuRole> implements MenuRoleService {

    private final MenuRoleMapper mapper;

    @Autowired
    public MenuRoleServiceImpl(MenuRoleMapper mapper) {
        this.mapper = mapper;
        super.setMapper(mapper);
    }

    @Override
    public void deleteByRoleIdAndMenuId(MenuRole menuRole) {
        mapper.deleteByRoleIdAndMenuId(menuRole);
    }
}
