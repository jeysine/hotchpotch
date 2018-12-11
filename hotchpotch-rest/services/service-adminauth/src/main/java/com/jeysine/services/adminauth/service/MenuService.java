package com.jeysine.services.adminauth.service;

import com.jeysine.services.adminauth.entity.Menu;
import com.jeysine.services.adminauth.entity.MenuDto;
import com.jeysine.services.common.service.BaseService;

import java.util.List;

/**
 * @author jxyao
 * @date 2018-09-30
 */
public interface MenuService extends BaseService<Menu, MenuDto> {

    /**
     * 查询给目前用户能够操作的菜单
     * @param menuDto ,必须包含roleId参数
     * @return 用户有权限的菜单
     */
    List<MenuDto> findRoleMenuByCondition(MenuDto menuDto);

    /**
     * 查询给目前用户能够操作的菜单
     * @param menuDto ,必须包含roleId参数
     * @return 用户有权限的菜单
     */
    List<MenuDto> findUserMenuForAuth(MenuDto menuDto);

    /**
     * 授权菜单
     * @param menuList 菜单数组 , 包括授权信息
     * @return 菜单数组, 包括授权信息
     */
    List<MenuDto> authMenu(List<MenuDto> menuList);
}
