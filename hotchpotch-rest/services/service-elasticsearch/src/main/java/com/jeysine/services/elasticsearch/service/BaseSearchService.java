package com.jeysine.services.elasticsearch.service;

import com.github.pagehelper.Page;
import com.jeysine.services.elasticsearch.document.Product;
import com.jeysine.services.elasticsearch.dto.ProductDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author yaojx
 */
public interface BaseSearchService<T> {

    /**
     * 搜 索
     * @param keyword
     * @param clazz
     * @return
     */
    List<T> query(String keyword, Class<T> clazz);

    /**
     * 搜索高亮显示
     * @param keyword       关键字
     * @param indexName     索引库
     * @param fieldNames    搜索的字段
     * @return
     */
    List<T> queryHit(String keyword, String indexName, Class<T> clazz, String ... fieldNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * 搜索高亮显示，返回分页
     * @param pageNo        当前页
     * @param pageSize      每页显示的总条数
     * @param keyword       关键字
     * @param indexName     索引库
     * @param fieldNames    搜索的字段
     * @return
     */
    Page<T> queryHitByPage(int pageNo,int pageSize,String keyword, String indexName, Class<T> clazz, String ... fieldNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    /**
     * 删除索引库
     * @param indexName
     * @return
     */
    void deleteIndex(String indexName);

    /**
     * 保存
     * @param documents
     */
    void save(T ... documents);

    /**
     * 删除
     * @param id
     */
    void delete(String id);

    /**
     * 清空索引
     */
    void deleteAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 查询全部
     * @return
     */
    List<T> getAll();
}
