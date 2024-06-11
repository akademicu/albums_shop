package com.akademicu.albums.repository;

import com.akademicu.albums.models.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band,Long> {
}
