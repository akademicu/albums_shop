package com.akademicu.albums.controller;

import com.akademicu.albums.dto.AlbumDto;
import com.akademicu.albums.models.Album;
import com.akademicu.albums.service.serviceImpl.AlbumService;
import com.akademicu.albums.service.serviceImpl.serviceImpl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Album>> allAlbums(){
        List<Album> albumList = albumService.getAllAlbums();

        return new ResponseEntity<>(albumList, HttpStatus.OK);
    }

}
