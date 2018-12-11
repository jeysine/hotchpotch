package com.jeysine.services.common.mapper;


import com.jeysine.services.common.entity.Base;

import java.util.List;

/**
 * @author yaojx
 * @date 2018-09-30
 */
public interface BaseMapper<M extends Base, QM extends M> {
    /**
     * 保存数据
     * @param entity 实体
     * @return
     */
    void save(M entity);

    /**
     * 批量保存数据
     * @param entities 实体
     * @return M
     */
    void saveBatch(List<M> entities);

    /**
     * 更新实体
     * @param entity 实体
     * @return M
     */
    void update(M entity);

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

    Integer count(QM qm);

}
