package com.trabalho.livraria.entities;

import jakarta.persistence.*;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "itens")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPedido;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<Contem> itens;
}