package com.thalesbensi.ZipURL.api.controllers;

import com.thalesbensi.ZipURL.domain.services.ShortUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorturl")
public class ShortUrlController {

    final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = shortUrlService.getOriginalUrl(shortCode);
        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", originalUrl)
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> createShortUrl(@RequestBody String originalUrl) {
        String shortedUrl = shortUrlService.createShortUrl(originalUrl);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(shortedUrl);
    }

}
