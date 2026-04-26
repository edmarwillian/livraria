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

import com.trabalho.livraria.entities.Cliente;
import com.trabalho.livraria.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null)
            throw new RuntimeException("Cliente não encontrado");
        return cliente;
    }

    @PostMapping
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {
        return service.salvar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Cliente não encontrado");

        cliente.setCodCliente(id);
        return service.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Cliente não encontrado");

        service.deletar(id);
    }
}