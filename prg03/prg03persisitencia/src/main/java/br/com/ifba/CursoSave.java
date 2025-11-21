package br.com.ifba;

import br.com.ifba.curso.entity.Curso; // Entidade que representa a tabela
import java.util.List;
import jakarta.persistence.EntityManager; // O Gerente de Entidades
import jakarta.persistence.EntityManagerFactory; // A Fábrica (pesada) de Gerentes
import jakarta.persistence.Persistence; // Classe de inicialização do JPA
import jakarta.persistence.NoResultException; // Exceção comum ao buscar um único resultado

/**
 * CLASSE DAO MANUAL (LEGADO) * * FUNÇÃO: Centralizar todas as operações de
 * banco de dados para a entidade Curso, * gerenciando manualmente as conexões e
 * transações JPA. * * ATENÇÃO: Esta classe NÃO é usada na sua arquitetura
 * Spring Boot atual. * Ela foi substituída pelo 'GenericDao' e pelo 'CursoDao',
 * que usam @PersistenceContext e @Transactional.
 */
public class CursoSave {

    /**
     * FÁBRICA DE ENTITYMANAGERS (Criação Única) * - 'static final': Garante que
     * este objeto pesado seja criado APENAS UMA VEZ quando a aplicação iniciar,
     * economizando recursos. -
     * Persistence.createEntityManagerFactory("gerenciamento_curso"): Lê o
     * arquivo 'persistence.xml' e inicializa a conexão com o banco de dados.
     */
    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("gerenciamento_curso");

    // =========================================================================
    // MÉTODOS DE ESCRITA (Requerem Transação)
    // =========================================================================
    /**
     * Salva um NOVO objeto Curso no banco de dados (SQL INSERT).
     */
    public void salvar(Curso curso) throws Exception {

        // 1. CRIAÇÃO DO ENTITYMANAGER
        // Deve ser criado para cada operação de banco, pois é leve e não é thread-safe.
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // 2. INÍCIO DA TRANSAÇÃO: OBRIGATÓRIO para operações de escrita.
            entityManager.getTransaction().begin();

            // 3. PERSISTIR: Marca o objeto para ser inserido no banco.
            entityManager.persist(curso);

            // 4. COMMIT: Executa a operação SQL e confirma no banco.
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            // 5. ROLLBACK: Em caso de erro, desfaz a transação para não salvar dados parciais.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o curso: " + e.getMessage());
            throw e; // Lança o erro para quem chamou (a tela)
        } finally {
            // 6. FECHAMENTO: Essencial para liberar a conexão de volta para o pool.
            entityManager.close();
        }
    }

    /**
     * Atualiza um objeto Curso EXISTENTE no banco de dados (SQL UPDATE).
     */
    public void atualizar(Curso curso) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // 1. MERGE: É o comando para ATUALIZAR. Ele "mescla" o objeto (que veio da tela
            // e está 'detached') com a instância gerenciada na sessão do banco.
            entityManager.merge(curso);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao ATUALIZAR o curso: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Exclui um objeto Curso do banco de dados (SQL DELETE).
     */
    public void excluir(Curso curso) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // 1. MERGE + REMOVE: Primeiro re-anexa o objeto para garantir que pode ser removido.
            Curso cursoGerenciado = entityManager.merge(curso);

            // 2. REMOVE: Marca o objeto gerenciado para ser deletado.
            entityManager.remove(cursoGerenciado);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir o curso: " + e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // =========================================================================
    // MÉTODOS DE LEITURA (Não requerem Transação)
    // =========================================================================
    /**
     * Busca todos os Cursos.
     */
    public List<Curso> listarTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // JPQL: Consulta que retorna todos os objetos 'Curso'.
            String jpql = "SELECT c FROM Curso c";

            // getResultList(): Executa o SELECT e retorna uma lista.
            return entityManager.createQuery(jpql, Curso.class).getResultList();

        } catch (Exception e) {
            System.err.println("Erro ao listar os cursos: " + e.getMessage());
            return null; // Retorna nulo se der erro.
        } finally {
            entityManager.close();
        }
    }

    /**
     * Busca um único Curso pelo 'codigoCurso'.
     */
    public Curso buscarPorCodigo(String codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // JPQL com parâmetro (:codigo)
            String jpql = "SELECT c FROM Curso c WHERE c.codigoCurso = :codigo";

            // 1. Cria a consulta.
            // 2. setParameter: Preenche o parâmetro.
            // 3. getSingleResult: Executa e espera APENAS UM resultado.
            return entityManager.createQuery(jpql, Curso.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();

        } catch (NoResultException e) {
            // Exceção específica para quando o SELECT não encontra ninguém.
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por código: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Busca um Curso pelo seu ID (chave primária).
     */
    public Curso buscarId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // find(): Método nativo e rápido para buscar pela chave primária.
            return entityManager.find(Curso.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por ID: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }
}
