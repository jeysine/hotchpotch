package com.jeysine.process.admin.controller.adminauth;


import com.github.pagehelper.PageInfo;
import com.jeysine.process.common.vo.ResponseVO;
import com.jeysine.services.adminauth.entity.UserDto;
import com.jeysine.services.adminauth.service.UserService;
import com.jeysine.services.common.exception.GlassesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping({"/private/user","/public/user"})
public class PrivateUserController {

    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(PrivateUserController.class);

    @Autowired
    public PrivateUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户列表
     * @param request http请求
     * @return 所有用户角色
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseVO getPage(@RequestBody UserDto user, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) throws ParseException, GlassesException {
        PageInfo<UserDto> page = userService.findByConditionPage(user, pageSize, pageNum);
        return new ResponseVO<PageInfo>().success(page);
    }

    /**
     * 根据id删除
     * @param user 包含id
     * @return responseVo
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseVO deleteById(@RequestBody UserDto user) {
        userService.deleteById(user.getId());
        return ResponseVO.success();
    }

    /**
     * 根据id删除
     * @param delUserIdList 待删除的用户列表
     * @return responseVo
     */
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseVO deleteBatch(@RequestBody List<String> delUserIdList) {
        userService.deleteByIdList(delUserIdList);
        return ResponseVO.success();
    }

    /**
     * 根据是否带id参数选择新增和修改
     * @param user 保存或修改的user
     * @return 返回user
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseVO saveOrUpdate(@RequestBody UserDto user) {
        try {
            userService.registerByAdmin(user);
        } catch (GlassesException e) {
            return ResponseVO.error(e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
        }
        return new ResponseVO<>().success(user);
    }

    @PostMapping("/active")
    public ResponseVO active(@RequestParam("username") String username, HttpServletRequest request) throws ParseException {
        userService.activeUser(username);
        return ResponseVO.success();
    }

    @PostMapping("/frozen")
    public ResponseVO frozen(@RequestParam("username") String username, HttpServletRequest request) throws ParseException {
        userService.frozenUser(username);
        return ResponseVO.success();
    }
}