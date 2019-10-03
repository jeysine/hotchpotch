package com.jeysine.services.jest.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.services.jest.service.BaseElasticsearchService;
import io.searchbox.core.Search;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaojx
 * @date 2019/7/9
 */
@Service
@Slf4j
public class BaseElasticsearchServiceImpl<T> implements BaseElasticsearchService<T>{

    @Autowired
    private RestHighLevelClient client;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public void createIndex(String index) throws Exception {
        CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        log.info("{}", OBJECT_MAPPER.writeValueAsString(response));
    }

    public boolean existsIndex(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    public void deleteIndex(String index) throws Exception {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        client.indices().delete(request, RequestOptions.DEFAULT);
    }
    public void get(String... indices) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indices);
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

    }

    public void search(String... indices) throws Exception {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        response.getHits();
    }

}
