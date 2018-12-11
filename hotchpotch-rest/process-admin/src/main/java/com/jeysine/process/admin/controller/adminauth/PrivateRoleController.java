package com.jeysine.process.admin.controller.adminauth;

import com.github.pagehelper.PageInfo;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.adminauth.entity.Role;
import com.jeysine.services.adminauth.service.RoleService;
import com.jeysine.services.common.exception.GlassesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * @author jxyao
 * @date 2018-9-30
 */
@RestController
@RequestMapping("/private/role")
public class PrivateRoleController {

    private final RoleService roleService;

    @Autowired
    public PrivateRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 角色列表
     * @param request http请求
     * @return 所有角色
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseVO getPage(@RequestBody Role role, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) throws ParseException, GlassesException {
        PageInfo<Role> page = roleService.findByConditionPage(role, pageSize, pageNum);
        return new ResponseVO<PageInfo>().success(page);
    }

    /**
     * 根据是否带id参数选择新增和修改
     * @param role 保存或修改的role
     * @return 返回role
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseVO saveOrUpdate(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return new ResponseVO<>().success(role);
    }

    /**
     * 根据条件查询
     * @param role 条件
     * @return 查询符合条件的role
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseVO get(Role role) {
        List<Role> roleList = roleService.findByCondition(role);
        return new ResponseVO<>().success(roleList);
    }
}
