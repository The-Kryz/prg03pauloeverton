/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import br.com.ifba.infrastructure.util.StringUtil; // Sua classe utilitária para checar textos
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CLASSE SERVICE (O GERENTE) * Função: Onde vive a REGRA DE NEGÓCIO. É aqui que
 * validamos dados (IFs e CHECAGENS) antes de deixar o DAO tocar no banco de
 * dados. * Analogia: O Gerente que verifica se o pedido está correto antes de
 * passar para o Estoquista.
 */
@Service // <--- ETIQUETA DO SPRING
// Diz ao Spring: "Isso aqui é um Serviço de Negócio. Deixe ele disponível para ser
// injetado (@Autowired) lá nos Controllers".
public class CursoService implements CursoIService {

    // @Autowired: O Gerente precisa do Estoquista (DAO) para trabalhar.
    // O Spring traz o DAO pronto e conecta aqui.
    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Método Salvar (Criar Novo). Aqui aplicamos as regras de validação
     * (negócio).
     */
    @Override
    public Curso save(Curso curso) {

        // REGRA 1: Validar Nome
        // Usamos o StringUtil para ver se é nulo ou vazio ("").
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            // Se estiver errado, lançamos uma EXCEÇÃO (RuntimeException).
            // Isso PARA tudo imediatamente. O DAO nem chega a ser chamado.
            // O Controller vai pegar essa mensagem e mostrar no JOptionPane.
            throw new RuntimeException("O nome do curso é obrigatório!");
        }

        // REGRA 2: Validar Código
        if (StringUtil.isNullOrEmpty(curso.getCodigoCurso())) {
            throw new RuntimeException("O código do curso é obrigatório!");
        }

        // SUCESSO: Se passou pelos IFs acima, o dado é confiável.
        // Mandamos o Estoquista (DAO) guardar no banco.
        return cursoRepository.save(curso);
    }

    /**
     * Método Atualizar (Editar Existente).
     */
    @Override
    public Curso update(Curso curso) {
        // Também validamos na atualização. Ninguém pode apagar o nome do curso
        // e tentar salvar vazio.
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso não pode ficar vazio!");
        }

        // Chama o método 'atualizar' (merge) do DAO.
        return cursoRepository.save(curso);
    }

    /**
     * Método Excluir.
     */
    @Override
    public void delete(Curso curso) {
        // Validação básica: não dá pra excluir o "nada".
        if (curso == null) {
            throw new RuntimeException("O curso para excluir não pode ser nulo.");
        }

        // Manda o DAO remover.
        cursoRepository.delete(curso);
    }

    /**
     * Método Listar. Geralmente não tem muita regra, é só um "passa-repassa"
     * para o DAO.
     */
    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    /**
     * Método Buscar Específico.
     */
    @Override
    public Curso findByCodigoCurso(String codigoCurso) {
        return cursoRepository.findByCodigoCurso(codigoCurso);
    }
}
