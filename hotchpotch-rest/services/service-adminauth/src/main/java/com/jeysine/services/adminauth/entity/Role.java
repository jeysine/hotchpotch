package com.jeysine.services.adminauth.entity;

import com.jeysine.services.common.entity.Base;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("RoleM")
public class Role extends Base {
    private String name;

    private String code;

    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return super.toString() + "Role{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
