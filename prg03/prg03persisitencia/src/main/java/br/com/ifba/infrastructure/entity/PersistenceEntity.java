/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Entity; // Anotação OBRIGATÓRIA. Diz ao JPA: "Esta classe é uma tabela!".
import jakarta.persistence.GeneratedValue; // Usado para definir como o ID será gerado (ex: auto-incremento).
import jakarta.persistence.GenerationType; // Define a ESTRATÉGIA de geração de ID (AUTO, IDENTITY, etc.).
import jakarta.persistence.Id; // Anotação OBRIGATÓRIA. Marca qual campo é a Chave Primária (Primary Key).

@MappedSuperclass
public class PersistenceEntity {

    /**
     * Campo da Chave Primária (Primary Key). O 'L' maiúsculo em 'Long' permite
     * que o valor seja 'null', o que é uma boa prática para entidades (embora a
     * chave primária nunca será nula no banco).
     */
    @Id // ANOTAÇÃO: Marca o campo 'id' como a chave primária da tabela.

    /**
     * ANOTAÇÃO: Define a estratégia de geração da chave primária.
     *
     * - GenerationType.AUTO: (O que você usou) Deixa o Hibernate "adivinhar".
     * Com o MySQL, isso geralmente faz o Hibernate criar uma tabela extra (ex:
     * 'curso_seq') para controlar os IDs. É funcional, mas não ideal.
     *
     * - GenerationType.IDENTITY: (RECOMENDADO PARA MYSQL) Diz ao Hibernate para
     * usar a coluna 'AUTO_INCREMENT' nativa do MySQL. É mais simples, mais
     * rápido e não cria tabelas extras. Recomendo mudar para 'IDENTITY' quando
     * puder.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
