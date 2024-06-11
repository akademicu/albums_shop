package com.akademicu.albums.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Band {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "band", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Album> albumList;
}
