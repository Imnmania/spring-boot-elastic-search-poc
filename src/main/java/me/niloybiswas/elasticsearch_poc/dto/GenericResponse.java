package me.niloybiswas.elasticsearch_poc.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GenericResponse extends BaseResponse {
    private String message;
}
