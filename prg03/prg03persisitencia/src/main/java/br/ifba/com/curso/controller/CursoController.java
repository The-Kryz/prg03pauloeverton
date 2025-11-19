/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ifba.com.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;
import java.util.List;

public class CursoController implements CursoIController {

    // O Controller fala com o Service
    private final CursoIService cursoService = new CursoService();

    @Override
    public Curso salvar(Curso curso) {
        return cursoService.salvar(curso);
    }

    @Override
    public Curso atualizar(Curso curso) {
        return cursoService.atualizar(curso);
    }

    @Override
    public void excluir(Curso curso) {
        cursoService.excluir(curso);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoService.listarTodos();
    }

    @Override
    public Curso buscarPorCodigo(String codigo) {
        return cursoService.buscarPorCodigo(codigo);
    }
}
