package com.trabalho.livraria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);
        if (pedido == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
        return ResponseEntity.ok(service.salvar(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        pedido.setNumeroPedido(id);
        return ResponseEntity.ok(service.salvar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null)
            return ResponseEntity.notFound().build();

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}