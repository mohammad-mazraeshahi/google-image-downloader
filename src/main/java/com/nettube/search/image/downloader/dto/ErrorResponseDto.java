package com.nettube.search.image.downloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
public class ErrorResponseDto {
    @Builder.Default
    private Date timestamp = new Date();
    @Schema(example = "422")
    private Integer status;
    @Schema(example = "unprocessable entity")
    private String message;
    @Builder.Default
    @Schema(example = "[{\"field\": \"count\",\"message\": \"invalid\",\"value\": \"-1\"}]")
    private List<FieldErrorDetailDto> errors = new ArrayList<>();
}