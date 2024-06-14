package com.akademicu.albums.repository;

import com.akademicu.albums.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    Album findByName(String name);
}
