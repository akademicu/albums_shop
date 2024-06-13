package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.models.Album;
import com.akademicu.albums.models.Genre;
import com.akademicu.albums.repository.GenreRepository;
import com.akademicu.albums.service.serviceImpl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceGenreImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public String genreNameToString(List<Genre> genreList) {
        List<String> genreToList = new ArrayList<>();
        genreList.forEach(genre -> {
            genreToList.add(genre.getGenreName());
        });
        return String.join("/",genreToList);
    }
}
