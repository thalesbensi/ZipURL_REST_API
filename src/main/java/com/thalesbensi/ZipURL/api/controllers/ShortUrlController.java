package com.thalesbensi.ZipURL.api.controllers;

import com.thalesbensi.ZipURL.api.dtos.ShortUrlRequestDTO;
import com.thalesbensi.ZipURL.api.dtos.ShortUrlResponseDTO;
import com.thalesbensi.ZipURL.domain.exceptions.ShortUrlNotFoundException;
import com.thalesbensi.ZipURL.domain.services.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("zip")
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("admin/list")
    public ResponseEntity<List<ShortUrlResponseDTO>> listAll() {
        return ResponseEntity.ok(shortUrlService.listAll());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = shortUrlService.getOriginalUrl(shortCode);
        if (originalUrl == null) {
            throw new ShortUrlNotFoundException(shortCode);
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }


    @PostMapping
    public ResponseEntity<String> createShortUrl(@Valid @RequestBody ShortUrlRequestDTO shortUrlRequestDTO) {
        String shortedUrl = shortUrlService.createShortUrl(shortUrlRequestDTO.originalUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(shortedUrl);
    }
}



