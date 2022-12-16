package com.conest.conest.dtos;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    @NotEmpty
    private String cpfUsuario;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String nomeUsuario;

    @NotEmpty
    private String emailUsuario;

    @NotEmpty
    private String senhaUsuario;

    @NotNull
    private int cargoOng;

}
