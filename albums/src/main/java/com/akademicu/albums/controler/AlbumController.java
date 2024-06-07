package com.akademicu.albums.controler;

import com.akademicu.albums.entites.Album;
import com.akademicu.albums.service.serviceImpl.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AlbumController {


    private AlbumServiceImpl albumService;
    @Autowired
    public AlbumController(AlbumServiceImpl albumService){this.albumService=albumService;}

    @PostMapping("/add")
    public ResponseEntity<String> addAlbumInDb(@RequestBody Album album){
        albumService.addAlbum(album);
        return new ResponseEntity<>("album have been added", HttpStatus.CREATED);
    }


}
