package com.conest.conest.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conest.conest.dtos.UsuarioDTO;
import com.conest.conest.services.UsuarioService;
import com.conest.conest.models.UsuarioModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        if(usuarioService.existsByCpf_CNPJ(usuarioDTO.getCpfUsuario())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse CPF ou CNPJ já esta cadastrado!");
        }

        if(usuarioService.existsByEmail(usuarioDTO.getEmailUsuario())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse email ja esta cadastrado!");
        }

        var usuarioModel = new UsuarioModel();

        BeanUtils.copyProperties(usuarioDTO, usuarioModel);
        usuarioModel.setDtCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping("")
    public ResponseEntity<List<UsuarioModel>> listUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDTO usuarioDTO){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
    
    var usuarioModel = new UsuarioModel();
    BeanUtils.copyProperties(usuarioDTO, usuarioModel);
    usuarioModel.setId_usuario(usuarioModelOptional.get().getId_usuario());
    usuarioModel.setDtCadastro(usuarioModelOptional.get().getDtCadastro());
    usuarioModel.setCargoOng(usuarioModelOptional.get().getCargoOng());

    return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModel));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado Com Sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") UUID id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(!usuarioModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }
}
