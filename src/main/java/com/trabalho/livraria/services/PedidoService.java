package com.trabalho.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.livraria.entities.Pedido;
import com.trabalho.livraria.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido salvar(Pedido obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}