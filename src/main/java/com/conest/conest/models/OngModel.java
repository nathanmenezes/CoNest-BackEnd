package com.conest.conest.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ONG")
@Getter
@Setter
public class OngModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_ong;
    
    private String tour;
    
    private String fotoPerfil;
    
    private String capaPerfil;
    
    private String categoria;

    private String nomeOng;

    private String enderecoOng;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioModel usuarioModel;
}
