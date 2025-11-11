/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;

/**
 *
 * @author dudan
 * @param <Entity>
 */
public interface GenericIDao<Entity extends PersistenceEntity> {

    public abstract Entity salvar(Entity obj);

    public abstract Entity atualizar(Entity obj);

    public abstract void excluir(Entity obj);

    public abstract List<Entity> listarTodos();

    public abstract Entity buscarId(Long id);

}
