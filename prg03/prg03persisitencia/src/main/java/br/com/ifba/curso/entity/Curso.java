/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * CLASSE ENTIDADE (O MOLDE DA TABELA) * Função: Representar a tabela 'curso' do
 * banco de dados dentro do Java. * Analogia: É como uma Ficha de Cadastro em
 * branco. Cada variável (nome, código) é um campo dessa ficha. * Quando o
 * Spring roda, ele lê essa classe e pensa: "Opa, preciso criar uma tabela no
 * banco com esses campos aqui".
 */
@Entity // <--- CRUCIAL: Diz ao Spring/JPA que isso vira uma Tabela.
@Table(name = "curso") // Opcional: Define o nome exato da tabela no banco (ex: 'tbl_curso').
public class Curso extends PersistenceEntity implements Serializable {

    // 'extends PersistenceEntity': 
    // Herança! Em vez de digitar 'Long id' em todas as classes (Curso, Aluno, Professor),
    // nós herdamos dessa classe pai. Assim, o 'Curso' ganha um ID automaticamente.
    /**
     * Coluna do Nome.
     *
     * @Column: Permite configurar detalhes do banco. nullable = false: Diz que
     * NÃO pode salvar curso sem nome (Not Null).
     */
    @Column(name = "nome", nullable = false)
    private String nome;

    /**
     * Coluna do Código. unique = true: Garante que o banco bloqueie se tentarem
     * salvar dois cursos com o mesmo código (ex: dois cursos 'INF01').
     */
    @Column(name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;

    /**
     * Coluna Ativo. Usado para "Exclusão Lógica". Em vez de deletar do banco,
     * só marcamos como false.
     */
    @Column(name = "ativo")
    private boolean ativo;

    // --- GETTERS E SETTERS ---
    // O Java (e o Spring) precisa desses métodos para ler e escrever 
    // nas variáveis privadas acima. É como se fossem as canetas para preencher a ficha.
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // OBS: O getId() e setId() não estão aqui porque foram herdados do 'PersistenceEntity'.
}
