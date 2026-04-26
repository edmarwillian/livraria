package com.trabalho.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.livraria.entities.Estoque;
import com.trabalho.livraria.repositories.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    public List<Estoque> listar() {
        return repository.findAll();
    }

    public Estoque buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estoque salvar(Estoque obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}