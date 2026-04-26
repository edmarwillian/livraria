package com.trabalho.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.livraria.entities.Cliente;
import com.trabalho.livraria.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente salvar(Cliente obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}