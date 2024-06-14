package com.akademicu.albums.service.serviceImpl.serviceImpl;

import com.akademicu.albums.models.Band;
import com.akademicu.albums.repository.BandRepository;
import com.akademicu.albums.service.serviceImpl.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandServiceImpl implements BandService {

    @Autowired
    private BandRepository bandRepository;

    @Override
    public Band getBandById(Long id) {
        Optional<Band> band = bandRepository.findById(id);
        return band.orElse(null);
    }

    @Override
    public Band getBandByName(String bandName) {
        Band band = bandRepository.findByName(bandName);
        if (band == null){
            band = new Band();
            band.setName(bandName);
            bandRepository.save(band);
            band = bandRepository.findByName(bandName);
        }
        return band;
    }

    @Override
    public Band getBandByNameOnly(String bandName) {
        return bandRepository.findByName(bandName);
    }
}
