package com.conest.conest.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conest.conest.models.OngModel;
import com.conest.conest.repositories.OngRepository;

import jakarta.transaction.Transactional;

@Service
public class OngService {

    @Autowired
    OngRepository ongRepository;

    @Transactional
    public OngModel save(OngModel ongModel) {
        return ongRepository.save(ongModel);
    }

    public List<OngModel> findAll() {
        return ongRepository.findAll();
    }

    public Optional<OngModel> findById(UUID id) {
        return ongRepository.findById(id);
    }

    @Transactional
    public void delete(OngModel ongModel) {
        ongRepository.delete(ongModel);
    }

    
    
}
