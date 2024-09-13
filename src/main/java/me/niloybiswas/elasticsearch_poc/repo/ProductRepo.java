package me.niloybiswas.elasticsearch_poc.repo;

import me.niloybiswas.elasticsearch_poc.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product, String> {
}
