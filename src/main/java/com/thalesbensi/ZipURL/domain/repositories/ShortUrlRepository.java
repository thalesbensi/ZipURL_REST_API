package com.thalesbensi.ZipURL.domain.repositories;

import com.thalesbensi.ZipURL.domain.models.ShortURL;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRepository
        extends CrudRepository<ShortURL,String> {
}
