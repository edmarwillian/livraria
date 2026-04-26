package com.trabalho.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.livraria.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}