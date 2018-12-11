package com.jeysine.services.adminauth.entity;

import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("MenuQM")
public class MenuDto extends Menu {
    private String roleId;

    private String userId;

    private String menuRoleId;

    /**
     * 该角色是否授权该菜单
     */
    private Boolean requireAuth;

    /**
     * 是否修改过该菜单授权
     */
    private Boolean editAuth;

    /**
     * 子菜单
     */
    private List<MenuDto> subs;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuRoleId() {
        return menuRoleId;
    }

    public void setMenuRoleId(String menuRoleId) {
        this.menuRoleId = menuRoleId;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Boolean getEditAuth() {
        return editAuth;
    }

    public void setEditAuth(Boolean editAuth) {
        this.editAuth = editAuth;
    }

    public List<MenuDto> getSubs() {
        return subs;
    }

    public void setSubs(List<MenuDto> subs) {
        this.subs = subs;
    }

    @Override
    public String toString() {
        return super.toString() + "MenuDto{" +
                "roleId='" + roleId + '\'' +
                ", userId='" + userId + '\'' +
                "requireAuth='" + requireAuth + '\'' +
                "subs='" + subs + '\'' +
                '}';
    }
}
