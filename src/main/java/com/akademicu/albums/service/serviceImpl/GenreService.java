package com.akademicu.albums.service.serviceImpl;

import com.akademicu.albums.models.Album;
import com.akademicu.albums.models.Genre;

import java.util.List;

public interface GenreService {
    String genreNameToString(List<Genre> genreList);
}
