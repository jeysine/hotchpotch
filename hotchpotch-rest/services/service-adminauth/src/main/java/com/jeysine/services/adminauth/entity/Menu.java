package com.jeysine.services.adminauth.entity;

import com.jeysine.services.common.entity.Base;
import org.apache.ibatis.type.Alias;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@Alias("MenuM")
public class Menu extends Base {

    private String index;

    private String title;

    private String icon;

    private Boolean keepAlive;

    private String parentId;

    private Integer level;


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + "Menu{" +
                ", index='" + index + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", keepAlive=" + keepAlive +
                ", parentId='" + parentId + '\'' +
                ", level=" + level +
                '}';
    }
}
