package com.trabalho.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.livraria.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}