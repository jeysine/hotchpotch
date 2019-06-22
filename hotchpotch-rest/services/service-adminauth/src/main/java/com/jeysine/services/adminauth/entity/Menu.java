package com.jeysine.services.adminauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeysine.services.common.entity.Base;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("MenuM")
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends Base {

    private String index;

    private String title;

    private String icon;

    private Boolean keepAlive;

    private String parentId;

    private Integer level;
}
