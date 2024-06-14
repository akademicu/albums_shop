package com.akademicu.albums.repository;

import com.akademicu.albums.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    Album findByName(String name);

    @Query(value = "SELECT * FROM album \n" +
            "JOIN genre_album_mapping on genre_album_mapping.album_id=album.id \n" +
            "WHERE genre_album_mapping.genre_id= :genreId", nativeQuery = true)
    List<Album> getAlbumsByGenre(@Param("genreId") Long genreId);

    @Query(value = "SELECT * FROM album WHERE band_id=:bandId", nativeQuery = true)
    List<Album> getAlbumsByBand(@Param("bandId") Long bandId);

}
