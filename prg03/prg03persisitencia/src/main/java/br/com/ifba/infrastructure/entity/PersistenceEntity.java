/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * CLASSE ENTIDADE BASE * Função: Criar os campos que são comuns a TODAS as
 * tabelas do seu sistema, como a Chave Primária (ID). * Analogia: O Documento
 * de Identidade Base. Toda pessoa (Entidade) tem que ter um ID, então definimos
 * a estrutura dele aqui.
 */
@MappedSuperclass // <--- ANOTAÇÃO CHAVE: Diz para o JPA:
// "Não crie uma tabela para esta classe no banco de dados. 
// Apenas use seus campos como herança (template) para quem a estender."
public class PersistenceEntity {

    /**
     * Campo da Chave Primária (ID)
     */
    @Id // ANOTAÇÃO: Marca o campo 'id' como a Chave Primária (PK).

    /**
     * @GeneratedValue: Define como o valor do ID será gerado. * strategy =
     * GenerationType.AUTO: * Diz ao Hibernate para escolher a melhor forma de
     * gerar o ID * para o seu banco de dados (MySQL/Postgres/etc.). *
     * RECOMENDAÇÃO: Se você usa MySQL, é melhor usar:
     * @GeneratedValue(strategy = GenerationType.IDENTITY) Isso força o
     * Hibernate a usar o recurso nativo 'AUTO_INCREMENT' do MySQL, o que é mais
     * limpo e não cria tabelas extras de controle de sequência.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // --- GETTERS E SETTERS ---
    // Métodos essenciais para o Hibernate conseguir ler e escrever o ID da Entidade.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}