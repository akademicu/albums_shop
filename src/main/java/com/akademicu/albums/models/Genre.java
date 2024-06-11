package com.akademicu.albums.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Genre {

    @Id
    private Long id;
    private String genreName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "genre_album_mapping", joinColumns = @JoinColumn(name = "genre_id"),
                        inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albums;
}
