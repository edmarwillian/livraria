package com.trabalho.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.livraria.entities.Livro;
import com.trabalho.livraria.repositories.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> listar() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        Optional<Livro> livro = repository.findById(id);
        return livro.orElse(null);
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}