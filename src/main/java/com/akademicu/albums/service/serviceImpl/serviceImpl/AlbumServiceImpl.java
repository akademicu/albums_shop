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

import static java.util.Objects.isNull;

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
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        if (albums.isEmpty())throw new RuntimeException("no albums in db");
        List<AlbumDto> albumDtoList = new ArrayList<>();
        for (Album album: albums) {
            albumDtoList.add(mapToDto(album));
        }
        return albumDtoList;
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
        if (isNull(album)) throw new RuntimeException("No search album");
        return album;
    }

    @Override
    public List<Album> getAlbumsByGenre(String genreName) {
        Genre genre = genreService.genreByName(genreName);
        if (isNull(genre)) throw new AlbumNotFoundExceptionClass("no such genre");
        Long genreId = genre.getId();
        List<Album> albumList = new ArrayList<>();
        albumRepository.getAlbumsByGenre(genreId).forEach(albumList::add);
        if (albumList.isEmpty()) throw new AlbumNotFoundExceptionClass("no albums with such genre");
        return albumList;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(()->new AlbumNotFoundExceptionClass("album not found"));
    }

    @Override
    public List<Album> getAlbumByBandName(String bandName) {
        Band band = bandService.getBandByNameOnly(bandName);
        if (isNull(band))throw new AlbumNotFoundExceptionClass("nu such band");
        Long bandId = band.getId();
        List<Album> albumList = new ArrayList<>();
        albumRepository.getAlbumsByBand(bandId).forEach(albumList::add);
        return albumList;
    }

    @Override
    public void deleteAlbumByName(String albumName) {
        Album album = albumRepository.findByName(albumName);
        if (isNull(album)) throw new AlbumNotFoundExceptionClass("album not found");
        albumRepository.deleteById(album.getId());
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void updatrAlbum(String albumName, AlbumDto albumDto) {
        Album album = albumRepository.findByName(albumName);
        Album newAlbum = mapToEntity(albumDto);
        newAlbum.setId(album.getId());
        //AlbumDto newAlbumDto = mapToDto(album);
        albumRepository.save(newAlbum);



    }

    ///Helping functions
    private Album mapToEntity(AlbumDto albumDto){
        Album album = new Album();
        album.setId(albumDto.id());
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
        //String bandName = bandService.getBandById(album.getId()).getName();
        String genres = genreService.genreNameToString(album.getGenreList());
        return new AlbumDto(
             album.getId(), album.getName(), album.getReleaseYear(), album.getNrOfCopies(), album.getBandName(), genres
        );
    }
}
