package com.thalesbensi.ZipURL.domain.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class ShortURL {

    public ShortURL(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.numberOfViews = 0;
    }

    @Id
    private String id;

    private String originalUrl;
    private String shortCode;
    private final Date createdAt = new Date();
    private int numberOfViews;
}
