/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import org.springframework.stereotype.Repository;

/**
 * CLASSE DAO (O ESTOQUISTA) * Função: Conversar diretamente com o Banco de
 * Dados. É aqui que o SQL acontece. * Analogia: É o funcionário do
 * almoxarifado. O Gerente (Service) pede "Me vê o produto X", e o Estoquista
 * (DAO) sabe em qual prateleira (Tabela) buscar.
 */
@Repository // <--- ETIQUETA DO SPRING
// Essa anotação é CRUCIAL. Ela diz: "Spring, isso aqui cuida de Banco de Dados. 
// Se der erro de SQL, traduza para um erro que o Java entenda."
// Além disso, permite que o Service use @Autowired para chamar este DAO.
public class CursoDao extends GenericDao<Curso> implements CursoIDao {

    /**
     * Método específico que NÃO existe no Genérico. O GenericDao já nos deu:
     * salvar, atualizar, excluir, listarTodos e buscarPorId. Mas ele não sabe o
     * que é "buscarPorCodigo", então criamos aqui.
     */
    @Override
    public Curso buscarPorCodigo(String codigo) {

        // O 'entityManager' é a nossa conexão com o banco.
        // De onde ele veio? Da classe pai (GenericDao), por isso podemos usar o 'this'.
        // Não precisa fazer 'new EntityManager()', o Spring já injetou lá no pai.
        try {
            // JPQL (Java Persistence Query Language)
            // É um SQL "gourmet". Em vez de falar o nome da tabela (tbl_curso),
            // falamos o nome da Classe (Curso) e dos atributos (c.codigoCurso).
            // :codigo -> É um "buraco" (parâmetro) que vamos preencher depois.
            String jpql = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigo";

            // 1. Cria a consulta.
            // 2. setParameter: Troca o ":codigo" pelo valor real que veio no parâmetro.
            //    (Isso evita Hackers e SQL Injection!).
            // 3. getSingleResult: "Banco, me dá UM resultado só".
            return entityManager.createQuery(jpql, Curso.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();

        } catch (jakarta.persistence.NoResultException e) {
            // Se o banco não achar nada, ele solta um grito (Exception).
            // Nós capturamos esse grito e devolvemos 'null' (vazio) elegantemente.
            return null;
        }

        // OBS: Não precisa fechar conexão (close). O Spring faz isso quando termina o trabalho.
    }

}
