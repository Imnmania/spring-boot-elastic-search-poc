package me.niloybiswas.elasticsearch_poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private int quantity;
    private double price;
}
