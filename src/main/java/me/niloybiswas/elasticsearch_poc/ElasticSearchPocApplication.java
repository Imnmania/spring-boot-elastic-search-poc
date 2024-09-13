package me.niloybiswas.elasticsearch_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class ElasticSearchPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchPocApplication.class, args);
    }

}
