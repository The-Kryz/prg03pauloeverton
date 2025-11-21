/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * INTERFACE DO CONTROLLER (O CONTRATO) * Função: Definir QUAIS métodos o
 * Controller OBRIGATORIAMENTE deve ter. Analogia: É o "Cardápio" ou a
 * "Descrição do Cargo". Ela não diz COMO fazer (não tem código dentro dos
 * métodos), só diz O QUE deve ser feito. * Por que usar? Para diminuir a
 * dependência. A tela conversa com a Interface ("Eu preciso de UM controller"),
 * sem precisar saber exatamente qual classe está rodando por trás.
 */
public interface CursoIController {

    // Define que quem implementar essa interface TEM que saber salvar um Curso.
    public abstract Curso salvar(Curso curso);

    // Tem que saber atualizar.
    public abstract Curso atualizar(Curso curso);

    // Tem que saber excluir.
    public abstract void excluir(Curso curso);

    // Tem que saber listar tudo.
    public abstract List<Curso> listarTodos();

    // Tem que saber buscar por código.
    public abstract Curso buscarPorCodigo(String codigo);

}
