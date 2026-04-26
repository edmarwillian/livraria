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

import com.trabalho.livraria.entities.Pedido;
import com.trabalho.livraria.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        if (pedido == null)
            throw new RuntimeException("Pedido não encontrado");
        return pedido;
    }

    @PostMapping
    public Pedido salvar(@RequestBody @Valid Pedido pedido) {
        return service.salvar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Pedido não encontrado");

        pedido.setNumeroPedido(id);
        return service.salvar(pedido);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            throw new RuntimeException("Pedido não encontrado");

        service.deletar(id);
    }
}