package com.thalesbensi.ZipURL.domain.repositories;

import com.thalesbensi.ZipURL.domain.models.ShortURL;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortUrlRepository extends MongoRepository<ShortURL, String> {
    Optional<ShortURL> findByShortCode(String shortCode);
}

