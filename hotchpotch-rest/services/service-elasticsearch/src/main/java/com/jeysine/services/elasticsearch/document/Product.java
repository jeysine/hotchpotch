package com.jeysine.services.elasticsearch.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaojx
 */
@Document(indexName = "orders", type = "product")
@Mapping(mappingPath = "productIndex.json")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    @Id
    private String id;

    private String productName;

    private String productDesc;

    private Date createTime;

    private Date updateTime;
}
