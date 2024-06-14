package com.akademicu.albums.service.serviceImpl;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album createAlbumInDb(AlbumDto albumDto);
    Album getAlbumByName(String albumName);
    List<Album> getAlbumsByGenre(String genreName);
}
