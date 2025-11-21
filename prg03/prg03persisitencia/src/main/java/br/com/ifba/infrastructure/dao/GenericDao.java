package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * CLASSE DAO GENÉRICA (A IMPLEMENTAÇÃO PADRÃO) * Função: Implementar todos os
 * métodos do contrato 'GenericIDao' de forma genérica. * Analogia: O Robô
 * Administrador. Faz o trabalho pesado de banco para todas as entidades.
 */
public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    /**
     * @PersistenceContext: A CHAVE DA CONEXÃO * Analogia: A Chave do Cofre. É o
     * objeto que sabe conversar com o banco. * Por que @PersistenceContext? O
     * Spring INJETA (traz pronto) o EntityManager * para nós. Não precisamos
     * mais do código antigo que criava a fábrica de conexões.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    @Transactional // <--- TRATAMENTO AUTOMÁTICO DE TRANSAÇÕES
    // Essa anotação faz o Spring: ABRIR Transação -> EXECUTAR o código -> FECHAR Transação (COMMIT ou ROLLBACK).
    // O programador NUNCA mais precisa escrever begin(), commit() ou close().
    public Entity salvar(Entity entity) {
        // persist(): Método do JPA que executa um SQL INSERT (criar um novo).
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Entity atualizar(Entity entity) {
        // merge(): Método do JPA que executa um SQL UPDATE (atualizar um existente).
        // Resolve o erro de 'detached entity passed to persist' que tivemos antes.
        return entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void excluir(Entity entity) {
        // Antes de remover, precisamos "trazer o objeto de volta à vida" na sessão atual.
        // Se a entidade veio de outra tela, ela pode estar "desligada" (detached).
        Entity entityParaRemover = entityManager.find(getTypeClass(), entity.getId());

        // Se achou, remove de fato.
        if (entityParaRemover != null) {
            // remove(): Executa um SQL DELETE.
            entityManager.remove(entityParaRemover);
        }
    }

    @Override
    public List<Entity> listarTodos() {
        // Para consultas (SELECTs), o @Transactional é opcional.
        // Cria um JPQL dinâmico: "SELECT * FROM NomeDaClasse"
        return entityManager.createQuery("from " + getTypeClass().getName()).getResultList();
    }

    @Override
    public Entity buscarId(Long id) {
        // find(): Busca um objeto pela chave primária (ID) de forma muito rápida.
        return entityManager.find(getTypeClass(), id);
    }

    /**
     * MÉTODO DE REFLEXÃO * Função: Descobrir qual é a classe real que está
     * usando esse DAO (Ex: Curso, Aluno). * Isso permite que a consulta SQL
     * (createQuery) e o find() funcionem corretamente, * mesmo que o código não
     * saiba o nome da classe até a hora de rodar.
     */
    protected Class<Entity> getTypeClass() {
        Class<Entity> clazz = (Class<Entity>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
}
