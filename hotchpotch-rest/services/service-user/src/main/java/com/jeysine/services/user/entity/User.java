package com.jeysine.services.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeysine.services.common.entity.Base;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author yaojx
 * @date 2018-10-08
 */
@Alias("UserM")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Base {
    /** 名字 */
    private String name;

    /** 性别，男，女 */
    private String sex;

    /** 用户名 */

    private String username;

    /** 密码 */
    private String password;

    /** 说明备注 */
    private String remark;

    /** 身份证号 */
    private String idCard;

    private Date birthday;

    private String nickname;

    private String category;

}
