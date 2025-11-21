/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericIDao;

/**
 * INTERFACE DO DAO (O CONTRATO DO ESTOQUE) * Função: Definir quais buscas
 * especiais essa entidade tem. * Analogia: É a lista de tarefas extras. Como
 * ela estende 'GenericIDao<Curso>', ela já "herda" a obrigação de ter: Salvar,
 * Excluir, Atualizar e Listar. Só precisamos adicionar o que for diferente.
 */
public interface CursoIDao extends GenericIDao<Curso> {

    // Aqui dizemos: "Quem for ser o DAO de Curso, TEM que saber buscar por código".
    // Não dizemos COMO (sem SQL aqui), só O QUE.
    public abstract Curso buscarPorCodigo(String codigo);

}
