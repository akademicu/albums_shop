package com.akademicu.albums.controller;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.exception.AlbumNotFoundExceptionClass;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.models.Genre;
import com.akademicu.albums.service.serviceImpl.serviceImpl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumServiceImpl albumService;
    @Autowired
    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }


    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAllAlbumsController(){
        List<AlbumDto> albumList = albumService.getAllAlbums();
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addAlbumController(@RequestBody AlbumDto albumDto){
        Album album = albumService.createAlbumInDb(albumDto);
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }

    @GetMapping("/album/{albumName}")
    public ResponseEntity<Album> getAlbumByNameController(@PathVariable String albumName){
        Album album = albumService.getAlbumByName(albumName);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("/genre/{genreName}")
    public ResponseEntity<List<Album>> getAllAlbumsByGeneController(@PathVariable String genreName){
        List<Album> albumList = albumService.getAlbumsByGenre(genreName);
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

    @GetMapping("/band/{bandName}")
    public ResponseEntity<List<Album>> getAlbumsByBandController(@PathVariable String bandName){
        List<Album> albumList = albumService.getAlbumByBandName(bandName);
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

    @DeleteMapping(params = "albumName")
    public ResponseEntity<String> deleteAlbumByNameController(@RequestParam String albumName){
        Album album = albumService.getAlbumByName(albumName);
        albumService.deleteAlbumByName(albumName);
        return new ResponseEntity<>("The album "+albumName+" have been deleted", HttpStatus.OK);
    }

    @PutMapping("/album/{albumName}")
    public ResponseEntity<Album> replaceAlbumByName(@RequestBody AlbumDto albumDto,@PathVariable String albumName){
        Album album = albumService.getAlbumByName(albumName);
        albumService.updatrAlbum(albumName, albumDto);
        return new ResponseEntity<>(albumService.getAlbumById(album.getId()), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<String> deleteById(@RequestParam Long id){
        albumService.deleteById(id);
        return new ResponseEntity<>("album have bin deleted", HttpStatus.OK);
    }

}
