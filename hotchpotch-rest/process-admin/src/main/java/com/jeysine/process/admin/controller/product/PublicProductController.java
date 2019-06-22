package com.jeysine.process.admin.controller.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeysine.services.common.util.UuidUtil;
import com.jeysine.services.elasticsearch.document.Product;
import com.jeysine.services.elasticsearch.repository.ProductDocumentRepository;
import com.jeysine.services.elasticsearch.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * @author yaojx
 * @date 2019/6/3
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class PublicProductController {
    @Autowired
    private ProductSearchService productSearchService;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchRepository elasticsearchRepository;

    @GetMapping("/")
    public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, JsonProcessingException {
        log.info("【创建索引前的数据条数】:删除");
        productSearchService.deleteAll();
        log.info("【创建索引前的数据条数】：{}",productSearchService.getAll().size());

        Product productDocument = new Product();
        productDocument.setId(UuidUtil.getUuid());
        productDocument.setProductName("无印良品 MUJI 基础润肤化妆水");
        productDocument.setProductDesc("无印良品 MUJI 基础润肤化妆水 高保湿型 200ml");
        productDocument.setCreateTime(new Date());


        Product productDocument1 = new Product();
        productDocument1.setId(UuidUtil.getUuid());
        productDocument1.setProductName("荣耀 V10 尊享版");
        productDocument1.setProductDesc("荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待");
        productDocument1.setCreateTime(new Date());

        Product productDocument2 = new Product();
        productDocument2.setId(UuidUtil.getUuid());
        productDocument2.setProductName("资生堂(SHISEIDO) 尿素红罐护手霜");
        productDocument2.setProductDesc("日本进口 资生堂(SHISEIDO) 尿素红罐护手霜 100g/罐 男女通用 深层滋养 改善粗糙");
        productDocument2.setCreateTime(new Date());

        //elasticsearchTemplate.createIndex(Product.class);
        //elasticsearchRepository.save(productDocument);
        productSearchService.save(productDocument, productDocument1, productDocument2);

        log.info("【创建索引ID】:{},{},{}", productDocument.getId(), productDocument1.getId(), productDocument2.getId());
        log.info("【创建索引后的数据条数】：{}", productSearchService.getAll().size());


        productSearchService.getAll().forEach(System.out::println);

        String keyword = "MUJI";
        String indexName = "orders";

        List<Product> searchHits = productSearchService.queryHit(keyword, indexName, Product.class, "productName","productDesc");
        log.info("【根据关键字搜索内容，命中部分高亮，返回内容】：{}", OBJECT_MAPPER.writeValueAsString(searchHits));
    }
}
