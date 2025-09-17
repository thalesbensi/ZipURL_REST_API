package com.thalesbensi.ZipURL.api.dtos;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;

public record ShortUrlRequestDTO(
        @NotBlank(message = "The URL can't be blank")
        @URL(message = "Invalid URL")
        String originalUrl
) {}
