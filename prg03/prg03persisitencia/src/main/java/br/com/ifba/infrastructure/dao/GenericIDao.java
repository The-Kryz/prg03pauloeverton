/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;

/**
 * INTERFACE DAO GENÉRICA (O CONTRATO PADRÃO) * Função: Criar um contrato
 * (regras) que todo DAO do sistema será OBRIGADO a seguir.
 * * <Entity extends PersistenceEntity>: Damos um nome temporário (Entity) para
 * qualquer classe que venha a usar este DAO (Curso, Aluno, Professor). O
 * 'extends PersistenceEntity' garante que essa classe tem um ID. * Analogia:
 * Lista de Tarefas Padrão. Toda entidade deve ter esses 5 métodos.
 */
public interface GenericIDao<Entity extends PersistenceEntity> {

    // 1. OBRIGAÇÃO: Tem que ter um método para salvar um NOVO objeto.
    public abstract Entity salvar(Entity obj);

    // 2. OBRIGAÇÃO: Tem que ter um método para atualizar um objeto EXISTENTE.
    public abstract Entity atualizar(Entity obj);

    // 3. OBRIGAÇÃO: Tem que ter um método para deletar um objeto.
    public abstract void excluir(Entity obj);

    // 4. OBRIGAÇÃO: Tem que ter um método para listar todos os objetos.
    public abstract List<Entity> listarTodos();

    // 5. OBRIGAÇÃO: Tem que ter um método para buscar um objeto pelo ID (Chave Primária).
    public abstract Entity buscarId(Long id);

}
