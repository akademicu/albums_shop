package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.repository.AlbumRepository;
import com.akademicu.albums.service.serviceImpl.AlbumService;
import com.akademicu.albums.service.serviceImpl.BandService;
import com.akademicu.albums.service.serviceImpl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final BandService bandService;
    private final GenreService genreService;
    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, BandService bandService, GenreService genreService) {
        this.albumRepository = albumRepository;
        this.bandService = bandService;
        this.genreService = genreService;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albumsResponses = new ArrayList<>();

        return albumRepository.findAll();
    }



    private Album mapToEntity(AlbumDto albumDto){
        Album album = new Album();
        album.setName(albumDto.name());
        album.setReleaseYear(albumDto.releaseYear());
        album.setNrOfCopies(albumDto.nrOfCopies());
        return null;
    }

    private AlbumDto mapToDto(Album album){
        String bandName = bandService.getBandById(album.getId()).getName();
        String genres = genreService.genreNameToString(album.getGenreList());
        return new AlbumDto(
                album.getName(), album.getReleaseYear(), album.getNrOfCopies(), bandName, genres
        );
    }
}
