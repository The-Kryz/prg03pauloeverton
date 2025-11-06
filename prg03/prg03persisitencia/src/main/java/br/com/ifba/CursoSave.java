package br.com.ifba;

import br.com.ifba.curso.entity.Curso; // Importa a classe Entidade, o "molde" dos nossos dados.
import java.util.List; // Usado pelo método 'listarTodos' para retornar uma coleção.
import jakarta.persistence.EntityManager; // O "Gerente de Entidades". É a ferramenta que de fato salva,
// atualiza, deleta e busca no banco.
import jakarta.persistence.EntityManagerFactory; // A "Fábrica de Gerentes". Objeto pesado,
// criado uma vez só, que gerencia a conexão com o banco.
import jakarta.persistence.Persistence; // Classe principal do JPA, usada para "ler" o persistence.xml
// e inicializar a EntityManagerFactory.

/**
 * Esta classe é um DAO (Data Access Object - Objeto de Acesso a Dados). O
 * objetivo desta classe é ISOLAR toda a lógica de banco de dados (JPA,
 * Hibernate, SQL) do resto da sua aplicação (as Telas/Views).
 *
 * Pense nela como um "Garçom": - A Tela (View) é o "Cliente". - O Banco de
 * Dados é a "Cozinha".
 *
 * A Tela (Cliente) não sabe como a Cozinha funciona. Ela apenas chama o Garçom
 * (DAO) e diz: "salve(curso)" ou "me traga a lista()".
 *
 * @author dudan
 */
public class CursoSave {

    /**
     * A Fábrica de EntityManagers (EntityManagerFactory).
     *
     * 1. 'private static final': - 'static': Significa que esta variável
     * pertence à CLASSE, e não a um objeto. Só existirá UMA cópia dela para
     * toda a aplicação. - 'final': Significa que ela só pode ser inicializada
     * UMA VEZ.
     *
     * 2. Por que? A 'EntityManagerFactory' é um objeto "caro" (pesado) de
     * criar. Ela lê o 'persistence.xml', configura o pool de conexões com o
     * banco, etc. Nós a criamos UMA VEZ quando a aplicação inicia e a
     * reutilizamos sempre.
     *
     * 3. Persistence.createEntityManagerFactory("gerenciamento_curso"): Este
     * comando "lê" seu arquivo 'src/main/resources/META-INF/persistence.xml',
     * procura pela "unidade de persistência" com o nome "gerenciamento_curso" e
     * cria a fábrica com base nessas configurações (URL, usuário, senha, etc.).
     */
    private static final EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("gerenciamento_curso");

    /**
     * Método público que salva um NOVO objeto Curso no banco de dados.
     * (Corresponde a um INSERT no SQL).
     *
     * @param curso O objeto Curso preenchido com dados (que veio da tela)
     */
    public void salvar(Curso curso) {

        /**
         * O "Gerente de Entidades" (EntityManager).
         *
         * 1. Por que NÃO é 'static'? O EntityManager é "barato" (leve) de criar
         * e é feito para operações curtas. Ele representa UMA "unidade de
         * trabalho" com o banco.
         *
         * 2. REGRA DE OURO: Crie um EntityManager, use-o para UMA operação (ou
         * uma série de operações em uma transação) e FECHE-O. NUNCA o guarde em
         * uma variável 'static'.
         */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // 3. Inicia uma "Transação".
            //    REGRA: Qualquer operação que MODIFICA o banco (INSERT, UPDATE, DELETE)
            //    DEVE, obrigatoriamente, estar dentro de uma transação.
            entityManager.getTransaction().begin();

            // 4. "Persiste" o objeto.
            //    'persist' é o comando do JPA para "salvar". Ele pega o objeto 'curso'
            //    (que foi criado na tela e ainda não existe no banco)
            //    e o torna "gerenciado" pelo JPA, marcando-o para inserção.
            entityManager.persist(curso);

            // 5. "Comita" (Confirma) a transação.
            //    Este é o comando que "efetiva" a operação.
            //    Até esta linha, o 'persist' estava apenas "agendado".
            //    O 'commit' executa o INSERT no banco de dados.
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            // 6. Bloco de Erro.
            //    Se qualquer coisa der errado (ex: o 'codigoCurso' já existe e
            //    ele é 'unique' no banco), uma exceção será lançada.

            // 7. Boa prática: Verifica se a transação ainda está ativa (antes de
            //    tentar desfazê-la).
            if (entityManager.getTransaction().isActive()) {
                // 8. "Rollback" (Desfaz) a transação.
                //    Este é o "botão de cancelar". Ele garante que NENHUMA
                //    alteração seja salva no banco, mantendo os dados consistentes.
                entityManager.getTransaction().rollback();
            }

            // 9. Loga o erro no console (para o programador ver o que deu errado).
            System.err.println("Erro ao salvar o curso: " + e.getMessage());

            // 10. Lança a exceção para cima (para quem chamou o 'salvar').
            //     Isso é IMPORTANTE para que a TELA (CursoAdicionar)
            //     possa "pegar" (catch) esse erro e mostrar um
            //     JOptionPane.ERROR_MESSAGE para o usuário.
            throw e;

        } finally {
            // 11. Bloco "Finalmente".
            //     Este bloco é EXECUTADO SEMPRE, não importa se o 'try' deu certo
            //     ou se o 'catch' foi acionado.

            // 12. FECHA o EntityManager.
            //     Esta é a linha MAIS IMPORTANTE da gestão de recursos.
            //     Fechar o 'EntityManager' devolve a conexão de banco de dados
            //     para o "pool de conexões".
            //     Se você NÃO fechar, você cria um "vazamento de conexões"
            //     que esgota os recursos do banco e trava sua aplicação.
            entityManager.close();
        }
    }

    /**
     * Busca todos os Cursos salvos no banco de dados. (Corresponde a um 'SELECT
     * * FROM ...' no SQL).
     *
     * @return Uma Lista (List<Curso>) de objetos Curso.
     */
    public List<Curso> listarTodos() {
        // 1. Cria um EntityManager para esta operação.
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // 2. Define a consulta.
            //
            //    "SELECT c FROM Curso c"
            //    - "FROM Curso c": "Selecione da Entidade chamada 'Curso'
            //      e dê a ela o apelido 'c'".
            //    - "SELECT c": "Retorne o objeto 'c' (o objeto Curso inteiro)".
            String jpql = "SELECT c FROM Curso c";

            // 3. Cria e executa a consulta.
            //    - 'createQuery(jpql, Curso.class)': Cria a consulta e informa
            //      ao JPA que o resultado será uma lista de objetos 'Curso'.
            //    - '.getResultList()': Executa a consulta (o 'SELECT') e
            //      retorna os resultados como uma Lista.
            List<Curso> cursos = entityManager.createQuery(jpql, Curso.class).getResultList();

            // 4. Retorna a lista para quem chamou (a tela CursoListar).
            return cursos;

        } catch (Exception e) {
            // 5. Em caso de erro na consulta...
            System.err.println("Erro ao listar os cursos: " + e.getMessage());
            // 6. Retorna 'null'. A tela (preencherTabela) deve verificar
            //    se a lista é nula antes de tentar usá-la.
            return null;
        } finally {
            // 7. Fecha o EntityManager, liberando a conexão.
            //    (Repare que 'listar' não precisa de transação, pois é
            //    apenas uma operação de leitura, não de escrita).
            entityManager.close();
        }
    }

    /**
     * Exclui um objeto Curso do banco de dados. (Corresponde a um DELETE no
     * SQL).
     *
     * @param curso O objeto Curso que deve ser removido.
     */
    public void excluir(Curso curso) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // 1. Inicia a transação (OBRIGATÓRIO para DELETE)
            entityManager.getTransaction().begin();

            // 2. Garante que o objeto 'curso' está "gerenciado" (attached)
            //    Se o 'curso' foi encontrado por outro EntityManager (ex: o do
            //    'buscarPorCodigo'), ele está "detached" (solto).
            //    'merge' o "re-anexa" a este EntityManager atual.
            Curso cursoGerenciado = entityManager.merge(curso);

            // 3. Remove o objeto "gerenciado"
            entityManager.remove(cursoGerenciado);

            // 4. Confirma (comita) a exclusão
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Desfaz em caso de erro
            }
            System.err.println("Erro ao excluir o curso: " + e.getMessage());
            throw e; // Lança o erro para a tela (View)
        } finally {
            entityManager.close(); // Sempre fecha o EntityManager
        }
    }

    // --- NOVO MÉTODO: BUSCAR POR CÓDIGO ---
    /**
     * Busca um único Curso no banco de dados usando o 'codigoCurso'.
     *
     * @param codigo O código do curso (ex: "prg03") a ser encontrado.
     * @return O objeto Curso encontrado, or 'null' se não for encontrado.
     */
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
            // Isso acontece se o 'getSingleResult()' não encontrar ninguém.
            // Não é um erro, apenas não encontrou.
            return null;
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por código: " + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void atualizar(Curso curso) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            // 1. Inicia a transação (OBRIGATÓRIO para UPDATE)
            entityManager.getTransaction().begin();

            // 2. "Mescla" (merge) o objeto.
            //    'merge' é o comando do JPA para "atualizar".
            //    Ele pega o objeto 'curso' (que veio da tela de edição)
            //    e atualiza seu registro correspondente no banco de dados.
            entityManager.merge(curso);

            // 3. Confirma (comita) a atualização
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            // 4. Se der erro (ex: violação de regra do banco)
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Desfaz
            }
            System.err.println("Erro ao ATUALIZAR o curso: " + e.getMessage());
            throw e; // Lança o erro para a tela (View)
        } finally {
            // 5. SEMPRE fecha o EntityManager
            entityManager.close();
        }
    }

}
