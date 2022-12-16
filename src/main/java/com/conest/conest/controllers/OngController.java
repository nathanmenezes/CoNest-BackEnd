package com.conest.conest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conest.conest.dtos.OngDTO;
import com.conest.conest.models.OngModel;
import com.conest.conest.services.OngService;
import com.conest.conest.services.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    OngService ongService;

    @Autowired
    UsuarioService usuarioService;
    

    @PostMapping("")
    public ResponseEntity<Object> saveOng(@RequestBody @Valid OngDTO ongDTO){
        if(ongDTO.getUsuarioModel() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("É necessario um usuario");
        }
        var ongModel = new OngModel();
        BeanUtils.copyProperties(ongDTO, ongModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ongService.save(ongModel));
    }

    @GetMapping("")
    public ResponseEntity<List<OngModel>> getAllOngs(){
        return ResponseEntity.status(HttpStatus.OK).body(ongService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOng(@PathVariable (value = "id") UUID id){
        Optional<OngModel> ongModelOptional = ongService.findById(id);
        if(!ongModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ONG não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ongModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOng(@PathVariable (value = "id") UUID id){
        Optional<OngModel> ongModelOptional = ongService.findById(id);
        if(!ongModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ONG não encontrada");
        }
        ongService.delete(ongModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ONG e Usuario Deletados com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOng(@PathVariable (value = "id") UUID id, @RequestBody @Valid OngDTO ongDTO){
        Optional<OngModel> ongModelOptional = ongService.findById(id);
        if(!ongModelOptional.isPresent()){ // se esse optional não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ONG não encontrada");
        }
        var ongModel = new OngModel();
        BeanUtils.copyProperties(ongDTO, ongModel);
        ongModel.setId_ong(ongModelOptional.get().getId_ong());
        return ResponseEntity.status(HttpStatus.CREATED).body(ongService.save(ongModel));
    }

}
