package com.akademicu.albums.controller;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.service.serviceImpl.serviceImpl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumServiceImpl albumService;
    @Autowired
    public AlbumController(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Album>> getAllAlbumsController(){
        System.out.println("\nalbumController");
        List<Album> albumList = albumService.getAllAlbums();
        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<Album> addAlbumController(@RequestBody AlbumDto albumDto){
        Album album = albumService.createAlbumInDb(albumDto);
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }

    @GetMapping("/album/{name}")
    public ResponseEntity<Album> getAlbumByNameController(@PathVariable String name){
        Album album = albumService.getAlbumByName(name);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

}
