package com.jeysine.services.adminauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("MenuQM")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
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

}
