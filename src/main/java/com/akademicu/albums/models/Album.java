package com.akademicu.albums.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Album {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int releaseYear;
    @Column(nullable = false)
    private int nrOfCopies;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "genre_album_mapping", joinColumns = @JoinColumn(name = "album_id"),
                        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList;
}
