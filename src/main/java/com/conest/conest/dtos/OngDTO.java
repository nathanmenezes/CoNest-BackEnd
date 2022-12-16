package com.conest.conest.dtos;

import com.conest.conest.models.UsuarioModel;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OngDTO {

    @NotEmpty
    private String tour;
    
    @NotEmpty
    private String fotoPerfil;
    
    @NotEmpty
    private String capaPerfil;
    
    @NotEmpty
    private String categoria;

    @NotEmpty
    private String nomeOng;

    @NotEmpty
    private String enderecoOng;
    
    private UsuarioModel usuarioModel;
}
