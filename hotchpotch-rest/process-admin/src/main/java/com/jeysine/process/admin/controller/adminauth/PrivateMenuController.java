package com.jeysine.process.admin.controller.adminauth;


import com.jeysine.process.common.constants.SystemConstants;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.adminauth.entity.MenuDto;
import com.jeysine.services.adminauth.service.MenuService;
import com.jeysine.services.common.constants.ApiCode;
import com.jeysine.services.common.exception.GlassesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@RestController
@RequestMapping({"/private/menu", "/public/menu"})
public class PrivateMenuController {

    private final MenuService menuService;

    private Logger logger = LoggerFactory.getLogger(PrivateMenuController.class);

    @Autowired
    public PrivateMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 查询所有菜单
     * @param request http请求
     * @return 所有菜单
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseVO get(MenuDto menu, HttpServletRequest request) throws GlassesException {
        List<MenuDto> menuList = menuService.findByCondition(menu);

        return new ResponseVO<List<MenuDto>>().success(menuList);
    }

    /**
     * 查询该用户对应的菜单( 用于渲染菜单 )
     * @param request http请求
     * @return ResponseVO
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseVO getUserMenu(MenuDto menu, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(SystemConstants.USER_SESSION_ID);
        String roleId = (String) request.getSession().getAttribute(SystemConstants.USER_SESSION_ROLE_ID);
        if (userId == null) {
            logger.error("user session is lost");
            return ResponseVO.error(ApiCode.STATUS_NOT_AUTH.getStatus(), ApiCode.STATUS_NOT_AUTH.getMessage());
        }
        menu.setUserId(userId);
        menu.setRoleId(roleId);
        List<MenuDto> menuList = menuService.findRoleMenuByCondition(menu);

        return new ResponseVO<List<MenuDto>>().success(menuList);
    }

    /**
     * 查询该用户对应的菜单( 用于授权 )
     * @param request http请求
     * @return ResponseVO
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseVO getUserMenuForAuth(MenuDto menu, HttpServletRequest request) {
        List<MenuDto> menuList = menuService.findUserMenuForAuth(menu);

        return new ResponseVO<List<MenuDto>>().success(menuList);
    }

    /**
     * 授权
     * @param request http请求
     * @return ResponseVO
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseVO auth(@RequestBody List<MenuDto> menuList, HttpServletRequest request) {
        menuService.authMenu(menuList);
        return new ResponseVO<List<MenuDto>>().success(menuList);
    }
}