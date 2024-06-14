package com.akademicu.albums.service.serviceImpl;

import com.akademicu.albums.models.Band;

public interface BandService {
    Band getBandById(Long id);
    Band getBandByName(String bandName);
    Band getBandByNameOnly(String bandName);
}
