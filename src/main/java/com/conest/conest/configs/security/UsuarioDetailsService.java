package com.conest.conest.configs.security;

import com.conest.conest.models.UsuarioModel;
import com.conest.conest.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + emailUsuario));
        return usuarioModel;
    }
}