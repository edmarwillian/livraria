package com.trabalho.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.livraria.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}