package com.trabalho.livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Estoque> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Estoque buscar(@PathVariable Long id) {
        Estoque estoque = service.buscarPorId(id);
        if (estoque == null)
            throw new RuntimeException("Estoque não encontrado");
        return estoque;
    }

    @PostMapping
    public Estoque salvar(@RequestBody @Valid Estoque estoque) {
        return service.salvar(estoque);
    }

    @PutMapping("/{id}")
    public Estoque atualizar(@PathVariable Long id, @RequestBody @Valid Estoque estoque) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Estoque não encontrado");

        estoque.setId(id);
        return service.salvar(estoque);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Estoque não encontrado");

        service.deletar(id);
    }
}