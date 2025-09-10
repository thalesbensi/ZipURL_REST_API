package com.thalesbensi.ZipURL.api.dtos;

import com.thalesbensi.ZipURL.domain.models.ShortURL;

import java.util.Date;

public record ShortUrlResponseDTO(String id, String originalUrl, String shortCode, Date createdAt, int numberOfViews) {

    public static ShortUrlResponseDTO fromEntity(ShortURL shortURL) {
        return new ShortUrlResponseDTO(
                shortURL.getId(),
                shortURL.getOriginalUrl(),
                shortURL.getShortCode(),
                shortURL.getCreatedAt(),
                shortURL.getNumberOfViews()
        );
    }
}
