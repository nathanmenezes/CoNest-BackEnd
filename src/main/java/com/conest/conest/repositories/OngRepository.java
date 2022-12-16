package com.conest.conest.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conest.conest.models.OngModel;

@Repository
public interface OngRepository extends JpaRepository<OngModel, UUID>{
    
}
