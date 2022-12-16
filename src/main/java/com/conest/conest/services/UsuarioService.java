package com.conest.conest.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conest.conest.models.UsuarioModel;
import com.conest.conest.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean existsByCpf_CNPJ(String cpfUsuario) {
        return usuarioRepository.existsByCpfUsuario(cpfUsuario);
    }

    public boolean existsByEmail(String emailUsuario) {
        return usuarioRepository.existsByEmailUsuario(emailUsuario);
    }

    @Transactional
    public UsuarioModel save(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }
    
    public List<UsuarioModel> list(){
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void delete(UsuarioModel usuarioModel) {
        usuarioRepository.delete(usuarioModel);
    }
}
