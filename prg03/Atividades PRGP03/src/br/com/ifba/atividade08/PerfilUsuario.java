/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade08;

/**
 *
 * @author dudan
 */
public class PerfilUsuario {

    private Long id;
    private String descricao;
    private String List;

    public PerfilUsuario(Long id, String descrição, String List) {
        this.id = id;
        this.descricao = descrição;
        this.List = List;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descricao;
    }

    public String getList() {
        return List;
    }

    public void setList(String List) {
        this.List = List;
    }

}
