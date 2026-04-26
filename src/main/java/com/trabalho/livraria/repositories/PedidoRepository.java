package com.trabalho.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.livraria.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}