package com.thalesbensi.ZipURL.domain.services;

import com.thalesbensi.ZipURL.api.dtos.ShortUrlResponseDTO;
import com.thalesbensi.ZipURL.domain.models.ShortURL;
import com.thalesbensi.ZipURL.domain.repositories.ShortUrlRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {

    final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Cacheable("urlList")
    public List<ShortUrlResponseDTO> listAll() {
        List<ShortURL> shortURLS = shortUrlRepository.findAll();
        return shortURLS.stream().map(ShortUrlResponseDTO::fromEntity).toList();
    }

    public String getOriginalUrl(String shortCode) {
        ShortURL shortURL = shortUrlRepository.findByShortCode(shortCode).orElse(null);
        if (shortURL != null) {
            shortURL.setNumberOfViews(shortURL.getNumberOfViews() + 1);
            shortUrlRepository.save(shortURL);
            return shortURL.getOriginalUrl();
        }
        return null;
    }

    @CacheEvict(value = "urlList", allEntries = true)
    public String createShortUrl(String originalUrl) {
        String shortCode = generateShortCode();
        ShortURL shortURL = new ShortURL(originalUrl, shortCode);
        shortUrlRepository.save(shortURL);
        return shortCode;
    }


    private String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * characters.length());
            shortCode.append(characters.charAt(index));
        }
        return shortCode.toString();
    }
}


