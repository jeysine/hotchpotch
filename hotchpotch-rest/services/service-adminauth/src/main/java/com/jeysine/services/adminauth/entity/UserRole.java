package com.jeysine.services.adminauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeysine.services.common.entity.Base;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("UserRoleM")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends Base {
    private String userId;

    private String roleId;
}
