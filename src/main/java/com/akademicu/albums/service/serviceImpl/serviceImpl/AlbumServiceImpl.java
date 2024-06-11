package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.models.Album;
import com.akademicu.albums.repository.AlbumRepository;
import com.akademicu.albums.service.serviceImpl.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albumsResponses = new ArrayList<>();

        return albumRepository.findAll();
    }
}
