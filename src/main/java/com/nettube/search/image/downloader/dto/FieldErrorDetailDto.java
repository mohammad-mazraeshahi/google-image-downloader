package com.nettube.search.image.downloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
public class FieldErrorDetailDto {
    @Schema(example = "count")
    private String field;
    @Schema(example = "must be greater than or equal to 1")
    private String message;
    @Schema(example = "-1")
    private Object value;
}