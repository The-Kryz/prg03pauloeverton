/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.EntityManager;

/**
 *
 * @author dudan
 */
public class CursoDao extends GenericDao<Curso> implements CursoIDao {

    @Override
    public Curso buscarPorCodigo(String codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // JPQL para buscar por um parâmetro específico
            String jpql = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigo";

            Curso curso = entityManager.createQuery(jpql, Curso.class)
                    .setParameter("codigo", codigo) // Define o valor do parâmetro :codigo
                    .getSingleResult(); // Espera UM resultado
            return curso;

        } catch (jakarta.persistence.NoResultException e) {

            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por código: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

}
