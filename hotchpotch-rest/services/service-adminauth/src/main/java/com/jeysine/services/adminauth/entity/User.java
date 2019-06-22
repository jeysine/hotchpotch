package com.jeysine.services.adminauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeysine.services.common.entity.Base;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("UserM")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Base{
    private String name;

    private String password;

    private Integer sex;

    private String account;

    private String dept;

    private Integer status;

}
