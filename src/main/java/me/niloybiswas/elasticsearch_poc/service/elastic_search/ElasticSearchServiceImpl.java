package me.niloybiswas.elasticsearch_poc.service.elastic_search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.niloybiswas.elasticsearch_poc.entity.Product;
import me.niloybiswas.elasticsearch_poc.utils.ElasticSearchUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;

    @Override
    public SearchResponse<Map> matchAllService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplier();
        SearchResponse<Map> searchResponse = elasticsearchClient
                .search(builder -> builder.query(supplier.get()), Map.class);
        log.info("search response: {}", searchResponse);
        return searchResponse;
    }

    @Override
    public SearchResponse<Product> matchAllProduct() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(builder -> builder.index("products").query(supplier.get()), Product.class);
        log.info("products search response: {}", searchResponse);
        return searchResponse;
    }

    @Override
    public SearchResponse<Product> matchProductWithName(String name) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplierWithName(name);
        SearchResponse<Product> searchResponse = elasticsearchClient
                .search(builder -> builder.index("products").query(supplier.get()), Product.class);
        log.info("products with name search response: {}", searchResponse);
        return searchResponse;
    }
}
