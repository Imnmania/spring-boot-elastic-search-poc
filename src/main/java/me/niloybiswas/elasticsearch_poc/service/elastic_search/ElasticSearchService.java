package me.niloybiswas.elasticsearch_poc.service.elastic_search;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import me.niloybiswas.elasticsearch_poc.entity.Product;

import java.io.IOException;
import java.util.Map;

public interface ElasticSearchService {

    public SearchResponse<Map> matchAllService() throws IOException;

    public SearchResponse<Product> matchAllProduct() throws IOException;

}
