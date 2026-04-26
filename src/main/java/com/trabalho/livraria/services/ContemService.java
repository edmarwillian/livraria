package com.trabalho.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.livraria.entities.Contem;
import com.trabalho.livraria.repositories.ContemRepository;

@Service
public class ContemService {

    @Autowired
    private ContemRepository repository;

    public List<Contem> listar() {
        return repository.findAll();
    }

    public Contem buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Contem salvar(Contem obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}