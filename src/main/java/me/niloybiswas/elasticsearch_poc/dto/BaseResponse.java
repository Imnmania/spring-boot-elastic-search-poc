package me.niloybiswas.elasticsearch_poc.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse {
    private Integer status;
}
