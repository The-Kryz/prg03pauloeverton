/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    protected static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("gerenciamento_curso");

    @Override
    public Entity salvar(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar a entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Entity atualizar(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;

        } catch (Exception e) {

            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Desfaz
            }
            System.err.println("Erro ao ATUALIZAR a Entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void excluir(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();

            Entity entityAnexada = entityManager.merge(entity);

            entityManager.remove(entityAnexada);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir a Entidade: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Entity> listarTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(("from " + getTypeClass().getName())).getResultList();
        } catch (Exception e) {

            System.err.println("Erro ao listar as Entidades: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

// CÓDIGO CORRETO
    @Override
    public Entity buscarId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Use try...finally para garantir que o EM sempre será fechado
        try {
            return (Entity) entityManager.find(getTypeClass(), id);
        } finally {
            entityManager.close(); // <-- ISTO É ESSENCIAL
        }
    }

    protected Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

}
