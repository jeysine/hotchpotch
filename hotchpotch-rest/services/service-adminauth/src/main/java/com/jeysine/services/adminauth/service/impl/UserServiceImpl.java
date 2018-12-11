package com.jeysine.services.adminauth.service.impl;

import com.jeysine.services.adminauth.entity.User;
import com.jeysine.services.adminauth.entity.UserDto;
import com.jeysine.services.adminauth.entity.UserRole;
import com.jeysine.services.adminauth.mapper.UserMapper;
import com.jeysine.services.adminauth.service.RoleService;
import com.jeysine.services.adminauth.service.UserRoleService;
import com.jeysine.services.adminauth.service.UserService;
import com.jeysine.services.common.constants.ApiCode;
import com.jeysine.services.common.constants.CommonConstants;
import com.jeysine.services.common.exception.GlassesException;
import com.jeysine.services.common.service.impl.BaseServiceImpl;
import com.jeysine.services.common.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jxyao
 * @date 2018-5-18
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService {

    private final UserMapper mapper;

    private final UserRoleService userRoleService;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserMapper mapper, UserRoleService userRoleService, RoleService roleService) {
        this.mapper = mapper;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        super.setMapper(mapper);
    }

    /**
     * 用于新增用户
     * @param user 用户
     * @return 新增后的用户
     */
    @Transactional(rollbackFor = Exception.class)
    public User saveOrUpdateUser(UserDto user) throws GlassesException {
        // 标志保存userRole还是更新
        boolean saveUserRole = false;

        String roleId = user.getRoleId();
        if (user.getId() == null) {
            checkUserAccountExist(user.getAccount());
            saveUserRole = true;
        }
        if (user.getPassword() != null){
            user.setPassword(Md5Util.MD5Encode(user.getPassword(), "UTF-8"));
        }

        saveOrUpdate(user);

        saveOrUpdateUserRole(user, roleId, saveUserRole);
        return user;
    }

    /**
     * 检测帐号是否已被注册
     * @param account 帐号
     * @throws GlassesException 帐号被注册的错误信息
     */
    private void checkUserAccountExist(String account) throws GlassesException {
        UserDto userDto = new UserDto();
        userDto.setAccount(account);

        userDto = findOneByCondition(userDto);

        if (userDto != null) {
            throw new GlassesException(ApiCode.USER_IS_EXIST);
        }
    }

    /**
     * 更新或新增用户时, 对user_role关联表的更新
     * @param user 用户实体类
     * @param roleId 角色id
     * @param saveUserRole 标志保存userRole还是更新
     */
    private void saveOrUpdateUserRole(UserDto user, String roleId, boolean saveUserRole) {
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        if (!saveUserRole) {
            userRole = userRoleService.findOneByCondition(userRole);
        }
        userRole.setRoleId(roleId);
        userRoleService.saveOrUpdate(userRole);
    }
    @Override
    public void registerByAdmin(UserDto user) throws GlassesException {
        user.setStatus(CommonConstants.AdminUserStatusEnum.ACTIVE.getValue());
        saveOrUpdateUser(user);
    }

    @Override
    public void frozenUser(String account) {
        User user = new User();
        user.setAccount(account);
        user.setStatus(CommonConstants.AdminUserStatusEnum.FROZEN.getValue());
        update(user);
    }

    @Override
    public void activeUser(String account) {
        User user = new User();
        user.setAccount(account);
        user.setStatus(CommonConstants.AdminUserStatusEnum.ACTIVE.getValue());
        update(user);
    }
}
