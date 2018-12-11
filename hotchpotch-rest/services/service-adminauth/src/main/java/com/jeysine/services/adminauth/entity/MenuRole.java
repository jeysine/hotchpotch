package com.jeysine.services.adminauth.entity;

import com.jeysine.services.common.entity.Base;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("MenuRoleM")
public class MenuRole extends Base {
    private String menuId;

    private String roleId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return super.toString() + "MenuRole{" +
                "menuId='" + menuId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
