package com.jeysine.services.elasticsearch.repository;

import com.jeysine.services.elasticsearch.document.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author yaojx
 * @date 2019/6/3
 */

@Component
public interface ProductDocumentRepository extends ElasticsearchRepository<Product, String> {
}
