package me.niloybiswas.elasticsearch_poc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private int quantity;
    private double price;
}
