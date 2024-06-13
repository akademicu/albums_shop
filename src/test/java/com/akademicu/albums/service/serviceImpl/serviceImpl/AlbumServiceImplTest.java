package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.controller.AlbumController;
import com.akademicu.albums.models.Album;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class AlbumServiceImplTest {

    @Mock
    private AlbumServiceImpl albumService;


    @InjectMocks
    private AlbumController albumController;

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
        objectMapper = new ObjectMapper();
    }



    @Test
    @DisplayName("GET /albums/all")
    void getAllAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();
        List<Genre> genreList = List.of(new Genre(1L,"rock"), new Genre(2L, "pop") );
        albums.add(new Album(1L,"name",1983, 5, "band1", genreList));

        when(albumService.getAllAlbums()).thenReturn(albums);

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/albums/all/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseYear").value(1983));

    }
}