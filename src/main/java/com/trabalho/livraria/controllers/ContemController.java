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

import com.trabalho.livraria.entities.Contem;
import com.trabalho.livraria.services.ContemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contem")
public class ContemController {

    @Autowired
    private ContemService service;

    @GetMapping
    public List<Contem> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Contem buscar(@PathVariable Long id) {
        Contem contem = service.buscarPorId(id);
        if (contem == null)
            throw new RuntimeException("Item não encontrado");
        return contem;
    }

    @PostMapping
    public Contem salvar(@RequestBody @Valid Contem contem) {
        return service.salvar(contem);
    }

    @PutMapping("/{id}")
    public Contem atualizar(@PathVariable Long id, @RequestBody @Valid Contem contem) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Item não encontrado");

        contem.setId(id);
        return service.salvar(contem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Item não encontrado");

        service.deletar(id);
    }
}