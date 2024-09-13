package me.niloybiswas.elasticsearch_poc.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GenericResponse extends BaseResponse {
    private String message;
}
