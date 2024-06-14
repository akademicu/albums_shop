package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.models.Band;
import com.akademicu.albums.repository.AlbumRepository;
import com.akademicu.albums.service.serviceImpl.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final BandServiceImpl bandService;
    private final GenreServiceImpl genreService;
    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, BandServiceImpl bandService, GenreServiceImpl genreService) {
        this.albumRepository = albumRepository;
        this.bandService = bandService;
        this.genreService = genreService;
    }

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        //System.out.println("\nalbum Service");
        albumRepository.findAll().forEach(albums::add);
        System.out.println(albums.getFirst());

        if (albums.isEmpty())throw new RuntimeException("no albums in db");
        return albums;
    }


    @Override
    public Album createAlbumInDb(AlbumDto albumDto) {
        Album album = new Album();
        album = mapToEntity(albumDto);
        albumRepository.save(album);
        return album;
    }

    private Album mapToEntity(AlbumDto albumDto){
        Album album = new Album();
        album.setName(albumDto.name());
        album.setReleaseYear(albumDto.releaseYear());
        Band band = bandService.getBandByName(albumDto.band());
        album.setBand(band);
        album.setBandName(band.getName());
        album.setNrOfCopies(albumDto.nrOfCopies());
        album.setGenreList(genreService.stringOfGenresToObject(albumDto.genres()));
        return album;
    }

    private AlbumDto mapToDto(Album album){
        String bandName = bandService.getBandById(album.getId()).getName();
        String genres = genreService.genreNameToString(album.getGenreList());
        return new AlbumDto(
                album.getName(), album.getReleaseYear(), album.getNrOfCopies(), bandName, genres
        );
    }
}
