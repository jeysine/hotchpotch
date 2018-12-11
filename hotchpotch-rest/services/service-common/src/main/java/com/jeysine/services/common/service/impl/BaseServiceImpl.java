package com.jeysine.services.common.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeysine.services.common.entity.Base;
import com.jeysine.services.common.entity.CommonContextHolder;
import com.jeysine.services.common.mapper.BaseMapper;
import com.jeysine.services.common.service.BaseService;
import com.jeysine.services.common.util.UuidUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author jxyao
 * @date 2018-09-30
 */
@SuppressWarnings("all")
public class BaseServiceImpl<M extends Base, QM extends M>  implements BaseService<M, QM> {
    private BaseMapper mapper;

    public void setMapper(BaseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public M save(M entity) {
        if (entity.getId() == null) {
            entity.setId(UuidUtil.getUuid());
        }
        entity.setCreateTime(new Date());
        entity.setCreateBy(CommonContextHolder.getUserName());
        mapper.save(entity);
        return entity;
    }

    @Override
    public List<M> saveBatch(List<M> entities) {
        if (entities.isEmpty()) {
            return null;
        }
        for (int i = 0; i < entities.size(); i ++) {
            entities.get(i).setId(UuidUtil.getUuid());
        }
        mapper.saveBatch(entities);
        return entities;
    }

    @Override
    public M update(M entity) {
        mapper.update(entity);
        return entity;
    }

    @Override
    public M saveOrUpdate(M entity) {
        if (entity.getId() == null) {
            entity.setId(UuidUtil.getUuid());
            entity.setCreateTime(new Date());
            return save(entity);
        }
        return update(entity);
    }

    @Override
    public List<QM> findAll() {
        return mapper.findAll();
    }

    @Override
    public void deleteById(String id) {
        mapper.deleteById(id);
    }

    @Override
    public void deleteByIdList(List<String> idList) {
        for (String id : idList) {
            deleteById(id);
        }
    }

    @Override
    public List<QM> findByCondition(QM qm) {
        return mapper.findByCondition(qm);
    }

    @Override
    public QM findOneByCondition(QM qm) {
        return (QM) mapper.findOneByCondition(qm);
    }

    @Override
    public PageInfo<QM> findByConditionPage(QM qm, int pageSize, int pageNum) throws ParseException {
        PageHelper.startPage(pageNum, pageSize);
        List<QM> list = findByCondition(qm);
        PageInfo<QM> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Integer count(QM qm) {
        return mapper.count(qm);
    }

    @Override
    public Boolean isExist(QM qm) {
        return findOneByCondition(qm) != null;
    }
}
