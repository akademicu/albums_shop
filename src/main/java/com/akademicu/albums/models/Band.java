package com.akademicu.albums.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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


    @OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Album> albumList = new ArrayList<>();
}
