package me.niloybiswas.elasticsearch_poc.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ApiResponse extends BaseResponse {
    private Object data;
}
