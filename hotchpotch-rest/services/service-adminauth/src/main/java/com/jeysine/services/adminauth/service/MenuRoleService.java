package com.jeysine.services.adminauth.service;

import com.jeysine.services.adminauth.entity.MenuRole;
import com.jeysine.services.common.service.BaseService;

/**
 * @author jxyao
 * @date 2018-09-30
 */
public interface MenuRoleService extends BaseService<MenuRole, MenuRole> {
    void deleteByRoleIdAndMenuId(MenuRole menuRole);
}
