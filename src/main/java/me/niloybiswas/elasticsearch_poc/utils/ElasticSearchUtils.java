package me.niloybiswas.elasticsearch_poc.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtils {

    public static Supplier<Query> supplier() {
        final Supplier<Query> supplier = () -> Query.of(builder -> builder.matchAll(matchAllQuery()));
        return supplier;
    }

    private static MatchAllQuery matchAllQuery() {
        final MatchAllQuery.Builder matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierWithName(String fieldValue) {
        final Supplier<Query> supplier = () -> Query.of(builder -> builder.match(matchQueryWithName(fieldValue)));
        return supplier;
    }

    private static MatchQuery matchQueryWithName(String fieldValue) {
        final MatchQuery.Builder matchQuery = new MatchQuery.Builder();
        return matchQuery
                .field("name")
                .query(fieldValue)
                .build();
    }

}
