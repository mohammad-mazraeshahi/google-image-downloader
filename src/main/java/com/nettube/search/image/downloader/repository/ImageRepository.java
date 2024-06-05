package com.nettube.search.image.downloader.repository;

import com.nettube.search.image.downloader.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    boolean existsByUrl(String url);

}
