package com.jeysine.services.elasticsearch.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;

/**
 * @author yaojx
 */
@Document(indexName = "product", type = "docs")
@Mapping(mappingPath = "productIndex.json")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    @Id
    private String id;

    private String productName;

    private String productDesc;

    private String createTime;

    private String updateTime;
}
