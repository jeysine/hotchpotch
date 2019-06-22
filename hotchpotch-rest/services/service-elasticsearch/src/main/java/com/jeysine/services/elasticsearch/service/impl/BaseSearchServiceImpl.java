package com.jeysine.services.elasticsearch.service.impl;

import com.github.pagehelper.Page;
import com.jeysine.services.common.mapper.BaseMapper;
import com.jeysine.services.common.util.MapUtils;
import com.jeysine.services.elasticsearch.dto.ProductDto;
import com.jeysine.services.elasticsearch.service.BaseSearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author yaojx
 * @date 2019-06-03
 */
@Service
public class BaseSearchServiceImpl<T> implements BaseSearchService<T> {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private ElasticsearchRepository<T, String> elasticsearchRepository;

    public void setElasticsearchRepository(ElasticsearchRepository<T, String> elasticsearchRepository) {
        this.elasticsearchRepository = elasticsearchRepository;
    }

    @Override
    public List query(String keyword, Class<T> clazz) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new QueryStringQueryBuilder(keyword))
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                // .withSort(new FieldSortBuilder("createTime").order(SortOrder.DESC))
                .build();

        return elasticsearchTemplate.queryForList(searchQuery,clazz);
    }

    @Override
    public List<T> queryHit(String keyword, String indexName, Class<T> clazz, String... fieldNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 构造查询条件,使用标准分词器.
        QueryBuilder matchQuery = createQueryBuilder(keyword,fieldNames);

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = createHighlightBuilder(fieldNames);

        // 设置查询字段
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(indexName)
                .setQuery(matchQuery)
                .highlighter(highlightBuilder)
                // 设置一次返回的文档数量，最大值：10000
                .setSize(10000)
                .get();

        // 返回搜索结果
        SearchHits hits = response.getHits();

        return getHitList(hits, clazz);
    }

    @Override
    public Page<T> queryHitByPage(int pageNo, int pageSize, String keyword, String indexName, Class<T> clazz, String... fieldNames) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 构造查询条件,使用标准分词器.
        QueryBuilder matchQuery = createQueryBuilder(keyword,fieldNames);

        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = createHighlightBuilder(fieldNames);

        // 设置查询字段
        SearchResponse response = elasticsearchTemplate.getClient().prepareSearch(indexName)
                .setQuery(matchQuery)
                .highlighter(highlightBuilder)
                .setFrom((pageNo-1) * pageSize)
                // 设置一次返回的文档数量，最大值：10000
                .setSize(pageNo * pageSize)
                .get();


        // 返回搜索结果
        SearchHits hits = response.getHits();

        Page<T> page = new Page<>(pageNo, pageSize);
        page.addAll(getHitList(hits, clazz));

        return page;
    }

    @Override
    public void deleteIndex(String indexName) {
        elasticsearchTemplate.deleteIndex(indexName);
    }

    @Override
    public void save(T ... documents) {
        if (documents.length > 0) {
            elasticsearchRepository.saveAll(Arrays.asList(documents));
        }
    }

    @Override
    public void delete(String id) {
        elasticsearchRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        elasticsearchRepository.deleteAll();
    }

    @Override
    public T getById(String id) {
        return elasticsearchRepository.findById(id).get();
    }

    @Override
    public List getAll() {
        List<T> list = new LinkedList<>();
        elasticsearchRepository.findAll().forEach(list::add);
        return list;
    }

    /**
     * 构造查询条件
     */
    private QueryBuilder createQueryBuilder(String keyword, String... fieldNames){
        // 构造查询条件,使用标准分词器.
        // matchQuery(),单字段搜索
        return QueryBuilders.multiMatchQuery(keyword,fieldNames)
                .analyzer("ik_max_word")
                .operator(Operator.OR);
    }
    /**
     * 构造高亮器

     */
    private HighlightBuilder createHighlightBuilder(String... fieldNames){
        // 设置高亮,使用默认的highlighter高亮器
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                // .field("productName")
                .preTags("<span style='color:red'>")
                .postTags("</span>");

        // 设置高亮字段
        for (String fieldName: fieldNames) {
            highlightBuilder.field(fieldName);
        }

        return highlightBuilder;
    }

    /**
     * 处理高亮结果
     */
    private List<T> getHitList(SearchHits hits, Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<T> list = new LinkedList<>();
        for(SearchHit searchHit : hits){
            T one = (T) MapUtils.getObject((HashMap<String, Object>) searchHit.getSourceAsMap(), clazz, MapUtils.FirstOneCaseEnum.LOWER);
            list.add(one);
        }
        return list;
    }

}
