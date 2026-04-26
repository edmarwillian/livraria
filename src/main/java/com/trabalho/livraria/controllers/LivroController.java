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

import com.trabalho.livraria.entities.Livro;
import com.trabalho.livraria.services.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<Livro> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        Livro livro = service.buscarPorId(id);
        if (livro == null)
            throw new RuntimeException("Livro não encontrado");
        return livro;
    }

    @PostMapping
    public Livro salvar(@RequestBody @Valid Livro livro) {
        return service.salvar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody @Valid Livro livro) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Livro não encontrado");

        livro.setCodLivro(id);
        return service.salvar(livro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Livro não encontrado");

        service.deletar(id);
    }
}