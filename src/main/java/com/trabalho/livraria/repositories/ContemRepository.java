package com.trabalho.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.livraria.entities.Contem;

public interface ContemRepository extends JpaRepository<Contem, Long> {
}