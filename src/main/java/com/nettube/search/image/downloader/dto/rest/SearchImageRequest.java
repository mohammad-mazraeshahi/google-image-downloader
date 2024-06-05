package com.nettube.search.image.downloader.dto.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchImageRequest {
    @NotBlank
    @NotEmpty
    @Length(min = 1, max = 255)
    private String q;
    @Max(Integer.MAX_VALUE)
    @Min(1)
    private Integer count = 10;
}
