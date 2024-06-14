package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.exception.AlbumNotFoundExceptionClass;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.models.Band;
import com.akademicu.albums.models.Genre;
import com.akademicu.albums.repository.AlbumRepository;
import com.akademicu.albums.service.serviceImpl.AlbumService;
import com.akademicu.albums.service.serviceImpl.BandService;
import com.akademicu.albums.service.serviceImpl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    //get all albums
    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        System.out.println(albums.getFirst());
        if (albums.isEmpty())throw new RuntimeException("no albums in db");
        return albums;
    }
    //Create album
    @Override
    public Album createAlbumInDb(AlbumDto albumDto) {
        Album album = new Album();
        album = mapToEntity(albumDto);
        albumRepository.save(album);
        return album;
    }



    @Override
    public Album getAlbumByName(String albumName) {
        Album album = new Album();
        album = albumRepository.findByName(albumName);
        if (Objects.isNull(album)) throw new RuntimeException("No search album");
        return album;
    }

    @Override
    public List<Album> getAlbumsByGenre(String genreName) {
        Genre genre = genreService.genreByName(genreName);
        if (Objects.isNull(genre)) throw new AlbumNotFoundExceptionClass("no such genre");
        Long genreId = genre.getId();
        List<Album> albumList = new ArrayList<>();
        albumRepository.getAlbumsByGenre(genreId).forEach(albumList::add);
        if (albumList.isEmpty()) throw new AlbumNotFoundExceptionClass("no albums with such genre");
        return albumList;
    }

    @Override
    public List<Album> getAlbumByBandName(String bandName) {
        Band band = bandService.getBandByNameOnly(bandName);
        if (Objects.isNull(band))throw new AlbumNotFoundExceptionClass("nu such band");
        Long bandId = band.getId();
        List<Album> albumList = new ArrayList<>();
        albumRepository.getAlbumsByBand(bandId).forEach(albumList::add);
        return albumList;
    }

    ///Helping functions
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
