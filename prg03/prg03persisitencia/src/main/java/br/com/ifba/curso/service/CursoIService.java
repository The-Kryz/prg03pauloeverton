/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;


import br.com.ifba.curso.entity.Curso;
import java.util.List;
/**
 *
 * @author dudan
 */
public interface CursoIService {
    
    public abstract Curso salvar(Curso curso);
    public abstract Curso atualizar(Curso curso);
    public abstract void excluir(Curso curso);
    public abstract List<Curso> listarTodos();
    public abstract Curso buscarPorCodigo(String codigo);
    
}
