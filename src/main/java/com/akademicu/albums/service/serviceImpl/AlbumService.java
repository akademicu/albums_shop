package com.akademicu.albums.service.serviceImpl;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;

import java.util.List;

public interface AlbumService {
    List<AlbumDto> getAllAlbums();
    Album createAlbumInDb(AlbumDto albumDto);
    Album getAlbumByName(String albumName);
    List<Album> getAlbumsByGenre(String genreName);
    List<Album> getAlbumByBandName(String bandName);
    void deleteAlbumByName(String albumName);
    void updatrAlbum(String albumName, AlbumDto albumDto);
    Album getAlbumById(Long id);
    void deleteById(Long id);
}
