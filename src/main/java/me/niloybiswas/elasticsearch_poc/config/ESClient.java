package me.niloybiswas.elasticsearch_poc.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.elasticsearch.client.RestClientBuilder.*;

@Component
@RequiredArgsConstructor
public class ESClient {

    private final Environment environment;
    private final HttpClientConfigCallback httpClientConfigCallback;

    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        final String elasticSearchHost = environment.getProperty("elasticsearch-host");
        final String elasticSearchPort = environment.getProperty("elasticsearch-port");
        final String elasticSearchProtocol = environment.getProperty("elasticsearch-protocol");
        final RestClientBuilder builder = RestClient.builder(new HttpHost(
                Objects.requireNonNull(elasticSearchHost),
                Integer.parseInt(Objects.requireNonNull(elasticSearchPort)),
                elasticSearchProtocol
        ));
        builder.setHttpClientConfigCallback(httpClientConfigCallback);
        final RestClient restClient = builder.build();
        final RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(restClientTransport);
    }
}
