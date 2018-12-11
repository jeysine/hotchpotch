package com.jeysine.services.adminauth.service.impl;

import com.jeysine.services.adminauth.entity.Menu;
import com.jeysine.services.adminauth.entity.MenuDto;
import com.jeysine.services.adminauth.entity.MenuRole;
import com.jeysine.services.adminauth.mapper.MenuMapper;
import com.jeysine.services.adminauth.service.MenuRoleService;
import com.jeysine.services.adminauth.service.MenuService;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jxyao
 * @date 2018-5-18
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDto> implements MenuService {

    private final MenuMapper mapper;

    private final MenuRoleService menuRoleService;

    @Autowired
    public MenuServiceImpl(MenuMapper mapper, MenuRoleService menuRoleService) {
        this.mapper = mapper;
        this.menuRoleService = menuRoleService;
        super.setMapper(mapper);
    }

    @Override
    public List<MenuDto> findRoleMenuByCondition(MenuDto menuDto) {
        menuDto.setKeepAlive(true);
        menuDto.setLevel(1);
        List<MenuDto> parentMenuList = mapper.findRoleMenuByCondition(menuDto);

        menuDto.setLevel(2);
        List<MenuDto> childMenuList = mapper.findRoleMenuByCondition(menuDto);

        if (childMenuList.isEmpty()) {
            return parentMenuList;
        }

        for (MenuDto parent: parentMenuList) {
            List<MenuDto> thisChildMenuList = new LinkedList<>();
            Iterator<MenuDto> it = childMenuList.iterator();
            while (it.hasNext()) {
                MenuDto thisChildMenu = it.next();
                if (thisChildMenu.getParentId() != null && thisChildMenu.getParentId().equals(parent.getId())) {
                    thisChildMenuList.add(thisChildMenu);
                    it.remove();
                }
            }
            parent.setSubs(thisChildMenuList);
        }
        return parentMenuList;
    }

    @Override
    public List<MenuDto> findUserMenuForAuth(MenuDto menuDto) {
        List<MenuDto> allMenu = findByCondition(new MenuDto());
        MenuRole menuRole = new MenuRole();
        menuRole.setRoleId(menuDto.getRoleId());
        List<MenuRole> menuRoleList = menuRoleService.findByCondition(menuRole);

        if (menuRoleList.isEmpty()) {
            return allMenu;
        }
        for (MenuDto menu: allMenu) {
            menu.setEditAuth(false);
            menu.setRequireAuth(false);
            Iterator<MenuRole> it = menuRoleList.iterator();
            while (it.hasNext()) {
                menuRole = it.next();
                if (menuRole.getMenuId().equals(menu.getId())) {
                    menu.setRequireAuth(true);
                    it.remove();
                }
            }
        }
        return allMenu;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<MenuDto> authMenu(List<MenuDto> menuList) {
        if (menuList.isEmpty()) {
            return null;
        }
        for (MenuDto menu: menuList) {
            if (menu.getEditAuth() != null && menu.getEditAuth()) {
                updateRoleAuth(menu);
            }
        }
        return menuList;
    }

    private void updateRoleAuth(MenuDto menu) {
        MenuRole menuRole = new MenuRole();
        menuRole.setMenuId(menu.getId());
        menuRole.setRoleId(menu.getRoleId());
        if (menu.getRequireAuth()) {
            menuRoleService.saveOrUpdate(menuRole);
            menu.setRequireAuth(true);
        } else {
            menuRoleService.deleteByRoleIdAndMenuId(menuRole);
            menu.setRequireAuth(false);
        }
        menu.setEditAuth(false);
    }
}
