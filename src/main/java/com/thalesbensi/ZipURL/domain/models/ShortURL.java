package com.thalesbensi.ZipURL.domain.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class ShortURL {

    @Id
    private String id;

    private String originaUrl;
    private String shortCode;
    private final Date createdAt = new Date();
    private int numberOfViews;
}
