package com.trabalho.livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.livraria.entities.Contem;
import com.trabalho.livraria.services.ContemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contem")
public class ContemController {

    @Autowired
    private ContemService service;

    @GetMapping
    public ResponseEntity<List<Contem>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contem> buscar(@PathVariable Long id) {
        Contem contem = service.buscarPorId(id);
        if (contem == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(contem);
    }

    @PostMapping
    public ResponseEntity<Contem> salvar(@RequestBody @Valid Contem contem) {
        return ResponseEntity.ok(service.salvar(contem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contem> atualizar(@PathVariable Long id, @RequestBody @Valid Contem contem) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        contem.setId(id);
        return ResponseEntity.ok(service.salvar(contem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}