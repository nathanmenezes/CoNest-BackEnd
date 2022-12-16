package com.conest.conest.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conest.conest.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID>{

    boolean existsByCpfUsuario(String cpfUsuario);
    boolean existsByEmailUsuario(String emailUsuario);

    Optional<UsuarioModel> findByEmailUsuario(String emailUsuario);
}
