/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;

public class CursoService implements CursoIService {

    // O Service fala com o DAO
    private final CursoIDao cursoDao = new CursoDao();

    @Override
    public Curso salvar(Curso curso) {
        // REGRA DE NEGÓCIO: Não permitir salvar curso sem nome
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso é obrigatório!");
        }

        // REGRA DE NEGÓCIO: Não permitir salvar curso sem código
        if (StringUtil.isNullOrEmpty(curso.getCodigoCurso())) {
            throw new RuntimeException("O código do curso é obrigatório!");
        }

        // Se passou nas regras, chama o DAO
        return cursoDao.salvar(curso);
    }

    @Override
    public Curso atualizar(Curso curso) {
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso não pode ficar vazio!");
        }
        return cursoDao.atualizar(curso);
    }

    @Override
    public void excluir(Curso curso) {
        if (curso == null) {
            throw new RuntimeException("O curso para excluir não pode ser nulo.");
        }
        cursoDao.excluir(curso);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoDao.listarTodos();
    }

    @Override
    public Curso buscarPorCodigo(String codigo) {
        return cursoDao.buscarPorCodigo(codigo);
    }
}
