package com.akademicu.albums.service.serviceImpl;

import com.akademicu.albums.entites.Album;
import com.akademicu.albums.repository.AlbumRepository;
import com.akademicu.albums.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository){this.albumRepository=albumRepository;}

    @Override
    public void addAlbum(Album album) {
        albumRepository.save(album);
    }
}
