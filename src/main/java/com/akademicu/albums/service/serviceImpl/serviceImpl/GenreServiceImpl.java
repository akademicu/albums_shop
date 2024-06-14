package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.models.Genre;
import com.akademicu.albums.repository.GenreRepository;
import com.akademicu.albums.service.serviceImpl.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre genreByName(String genreName) {
        return genreRepository.getGenreByGenreName(genreName);
    }

    @Override
    public String genreNameToString(List<Genre> genreList) {
        List<String> genreToList = new ArrayList<>();
        genreList.forEach(genre -> {
            genreToList.add(genre.getGenreName());
        });
        return String.join("/",genreToList);
    }

    @Override
    public List<Genre> stringOfGenresToObject(String strGenre) {
        List<Genre> genreList = new ArrayList<>();
        String[] genresArray = strGenre.split("/");
        for(String genreStr: genresArray){
            Genre genre = genreRepository.getGenreByGenreName(genreStr);
            if (genre == null){
                genre = new Genre();
                genre.setGenreName(genreStr);
                genreRepository.save(genre);
                genre = genreRepository.getGenreByGenreName(genreStr);
            }
            genreList.add(genre);
        }
        return genreList;
    }
}
