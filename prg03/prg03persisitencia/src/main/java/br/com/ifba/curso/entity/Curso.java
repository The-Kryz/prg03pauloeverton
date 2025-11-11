/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package br.com.ifba.curso.entity;

/*
 * Imports necessários para as anotações do JPA (Jakarta Persistence API).
 */
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity; // Anotação OBRIGATÓRIA. Diz ao JPA: "Esta classe é uma tabela!".
import java.io.Serializable;

/**
 * Esta é a classe de ENTIDADE. Ela é um "molde" (um POJO - Plain Old Java
 * Object) que representa os dados que serão salvos na tabela 'curso' no banco
 * de dados.
 *
 * O JPA/Hibernate usará esta classe para: 1. Criar automaticamente a tabela
 * 'curso' no banco (por causa do @Entity). 2. Mapear cada variável (campo)
 * abaixo para uma coluna na tabela. 3. Converter linhas da tabela em objetos
 * 'Curso' (quando fizermos 'listarTodos()'). 4. Converter objetos 'Curso' em
 * linhas na tabela (quando fizermos 'salvar(curso)').
 *
 * @author dudan
 */
@Entity // ANOTAÇÃO: Marca esta classe como uma Entidade gerenciada pelo JPA.
public class Curso extends PersistenceEntity implements Serializable {

    /**
     * Campo 'nome'. O JPA irá criar uma coluna chamada 'nome' no banco. Por
     * padrão, será um VARCHAR(255).
     */
    private String nome;

    /**
     * Campo 'codigoCurso'. O JPA irá criar uma coluna chamada 'codigoCurso' no
     * banco. (É uma boa prática adicionar @Column(unique = true) aqui para
     * garantir que não haja dois cursos com o mesmo código).
     */
    private String codigoCurso;

    /**
     * Campo 'ativo'. O JPA irá criar uma coluna 'ativo' no banco, geralmente do
     * tipo 'boolean' ou 'TINYINT(1)' no MySQL.
     */
    private boolean ativo;

    /*
     * GETTERS E SETTERS
     *
     * O JPA EXIGE que a classe de entidade tenha getters e setters públicos
     * (ou um construtor padrão) para que ele possa "hidratar" (preencher)
     * o objeto com dados do banco e ler dados do objeto para salvar no banco.
     *
     * 'getId' e 'setId' são usados para o ID.
     */
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

}
