package com.jeysine.services.common.entity;

import java.util.Date;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public class Base {
    private String id;

    private Date createTime;

    private String createBy;

    private Integer pageNum;

    private Integer pageSize;

    private Long currPageSize;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurrPageSize() {
        return currPageSize;
    }

    public void setCurrPageSize(Long currPageSize) {
        this.currPageSize = currPageSize;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", currPageSize=" + currPageSize +
                '}';
    }
}
