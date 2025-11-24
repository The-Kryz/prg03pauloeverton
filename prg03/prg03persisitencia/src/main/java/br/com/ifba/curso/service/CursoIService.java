/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * INTERFACE DO SERVICE (A DESCRIÇÃO DO CARGO DE GERENTE) * Função: Define quais
 * operações de negócio o sistema oferece para 'Curso'. * Analogia: É o contrato
 * de trabalho do Gerente. Diz: "O Gerente de Curso precisa saber salvar,
 * atualizar, excluir e listar". * Por que usar interface aqui? Para que o
 * Controller (Garçom) não precise saber QUEM é o gerente, apenas O QUE o
 * gerente faz. Isso facilita trocar a implementação no futuro.
 */
public interface CursoIService {

    // Métodos abstratos (sem código). A classe que implementar isso (CursoService)
    // será OBRIGADA a escrever o código dessas regras.
    public abstract Curso save(Curso curso);

    public abstract Curso update(Curso curso);

    public abstract void delete(Curso curso);

    public abstract List<Curso> findAll();

    public abstract Curso findByCodigoCurso(String codigo);

}
