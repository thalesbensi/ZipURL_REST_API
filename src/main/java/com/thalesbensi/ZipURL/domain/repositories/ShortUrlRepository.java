package com.thalesbensi.ZipURL.domain.repositories;

import com.thalesbensi.ZipURL.domain.models.ShortURL;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShortUrlRepository extends MongoRepository<ShortURL, String> {
    Optional<ShortURL> findByShortCode(String shortCode);
    Page<ShortURL> findAll(Pageable pageable);
}

