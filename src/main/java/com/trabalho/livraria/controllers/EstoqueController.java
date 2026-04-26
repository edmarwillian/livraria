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

import com.trabalho.livraria.entities.Estoque;
import com.trabalho.livraria.services.EstoqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping
    public ResponseEntity<List<Estoque>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscar(@PathVariable Long id) {
        Estoque estoque = service.buscarPorId(id);
        if (estoque == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(estoque);
    }

    @PostMapping
    public ResponseEntity<Estoque> salvar(@RequestBody @Valid Estoque estoque) {
        return ResponseEntity.ok(service.salvar(estoque));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estoque> atualizar(@PathVariable Long id, @RequestBody @Valid Estoque estoque) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        estoque.setId(id);
        return ResponseEntity.ok(service.salvar(estoque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}