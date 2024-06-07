package com.akademicu.albums.repository;

import com.akademicu.albums.entites.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
