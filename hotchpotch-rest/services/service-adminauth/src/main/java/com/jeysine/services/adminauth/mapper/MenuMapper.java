package com.jeysine.services.adminauth.mapper;


import com.jeysine.services.adminauth.entity.Menu;
import com.jeysine.services.adminauth.entity.MenuDto;
import com.jeysine.services.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu, MenuDto> {
    /**
     * 根据条件查询用户可以查看的菜单
     * @param menuDto
     * @return List
     */
    List<MenuDto> findRoleMenuByCondition(MenuDto menuDto);
}
