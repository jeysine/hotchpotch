package com.jeysine.services.elasticsearch.service.impl;

import com.jeysine.services.elasticsearch.document.Product;
import com.jeysine.services.elasticsearch.repository.ProductDocumentRepository;
import com.jeysine.services.elasticsearch.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaojx
 * @date 2019/6/3
 */
@Service
public class ProductSearchServiceImpl extends BaseSearchServiceImpl<Product> implements ProductSearchService {
    private final ProductDocumentRepository productDocumentRepository;

    @Autowired
    public ProductSearchServiceImpl(ProductDocumentRepository productDocumentRepository) {
        this.productDocumentRepository = productDocumentRepository;
        super.setElasticsearchRepository(productDocumentRepository);
    }


}
