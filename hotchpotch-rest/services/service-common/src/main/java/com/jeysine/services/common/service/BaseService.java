package com.jeysine.services.common.service;


import com.github.pagehelper.PageInfo;
import com.jeysine.services.common.entity.Base;

import java.text.ParseException;
import java.util.List;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public interface BaseService<M extends Base, QM extends M> {
    /**
     * 保存数据
     * @param entity 实体
     * @return M
     */
    M save(M entity);

    /**
     * 批量保存数据
     * @param entities 实体
     * @return M
     */
    List<M> saveBatch(List<M> entities);

    /**
     * 更新实体
     * @param entity 实体
     * @return M
     */
    M update(M entity);

    /**
     * 有id则更新实体, 否则保存
     * @param entity 实体
     * @return M
     */
    M saveOrUpdate(M entity);

    /**
     * 获取所有数据
     * @return QM
     */
    List<QM> findAll();

    /**
     * 根据id删除数据
     * @param id id
     */
    void deleteById(String id);

    /**
     * 根据id数组批量删除数据
     * @param idList id数组
     */
    void deleteByIdList(List<String> idList);

    /**
     * 根据字段查询
     * @param qm 实体
     * @return List<QM>
     */
    List<QM> findByCondition(QM qm);

    /***
     * 根据条件获取一条数据
     * @param qm 实体
     * @return QM
     */
    QM findOneByCondition(QM qm);

    /**
     * 查询分页
     * @param qm 包含条件
     * @param pageSize 页面数量
     * @param pageNum 页码
     * @return 分页信息
     */
    PageInfo<QM> findByConditionPage(QM qm, int pageSize, int pageNum) throws ParseException;

    /**
     * 统计
     * @param qm
     * @return
     */
    Integer count(QM qm);

    /**
     * 是否存在
     * @param qm
     * @return
     */
    Boolean isExist(QM qm);
}
