/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade08;

import java.time.LocalDateTime;

/**
 *
 * @author dudan
 */
public class LogAuditoria {

    private Long id;
    private Usuario usuario; // PRECISA SER INICIALIZADO
    private String acao;
    private LocalDateTime dataHora; // PRECISA SER INICIALIZADO
    private String ip;

    // Construtor corrigido para receber o Usuario e o ID ser gerado ou recebido
    public LogAuditoria(Usuario usuario, String acao, String ip) {
        this.id = (long) ((Math.random() * 10000) + 1); // Gerando um ID
        this.usuario = usuario;
        this.acao = acao;
        this.ip = ip;
        this.dataHora = LocalDateTime.now(); // Inicializa com o momento atual
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    // Adicionando o toString()
    @Override
    public String toString() {
        return "LogAuditoria{"
                + "id=" + id
                + ", usuario=" + usuario.getNomeUsuario()
                + // Acessa o nome do usuário
                ", acao='" + acao + '\''
                + ", dataHora=" + dataHora.toString().substring(0, 19)
                + // Formata a hora para ficar mais limpo
                ", ip='" + ip + '\''
                + '}';
    }
}
