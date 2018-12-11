package com.jeysine.services.adminauth.mapper;

import com.jeysine.services.adminauth.entity.MenuRole;
import com.jeysine.services.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole, MenuRole> {
    /**
     * 根据roleId和menuId删除记录
     * @param menuRole
     */
    void deleteByRoleIdAndMenuId(MenuRole menuRole);
}
