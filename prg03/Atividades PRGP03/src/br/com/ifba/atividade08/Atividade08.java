/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade08;

import java.time.LocalDateTime;

public class Atividade08 {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO TESTE DE POO (ATIVIDADE 08) ---");

        // 1. CRIAR PERFIS DE USUÁRIO
        // Construtor PerfilUsuario: (id, descricao, List)
        PerfilUsuario admin = new PerfilUsuario(1L, "Administrador", "Permissao: Total");
        PerfilUsuario convidado = new PerfilUsuario(2L, "Convidado", "Permissao: Leitura");

        System.out.println("\n[ PERFIS CRIADOS ]");
        System.out.println(admin); // Testa o toString() do PerfilUsuario
        System.out.println(convidado); // Testa o toString() do PerfilUsuario

        // 2. CRIAR USUÁRIOS E ASSOCIÁ-LOS AOS PERFIS
        // Construtor Usuario: (perfil, nomeUsuario, email, senha)
        Usuario usuario1 = new Usuario(admin, "joao.admin", "joao@ifba.br", "12345");
        usuario1.setId(101L); // Define o ID manualmente
        usuario1.setAtivo(true); // Ativa o usuário

        Usuario usuario2 = new Usuario(convidado, "maria.guest", "maria@ifba.br", "senhaforte");
        usuario2.setId(102L);
        usuario2.setAtivo(true);

        System.out.println("\n[ USUÁRIOS CRIADOS ]");
        System.out.println(usuario1); // Testa o toString() do Usuario
        System.out.println(usuario2); // Testa o toString() do Usuario

        // 3. CRIAR SESSÕES
        // Construtor Sessao: (usuario)
        Sessao sessaoJoao = new Sessao(usuario1);
        usuario1.setUltimoLogin(LocalDateTime.now()); // Atualiza o login do usuário

        Sessao sessaoMaria = new Sessao(usuario2);
        usuario2.setUltimoLogin(LocalDateTime.now());

        System.out.println("\n[ SESSÕES INICIADAS ]");
        System.out.println(sessaoJoao); // Testa o toString() da Sessao
        System.out.println(sessaoMaria); // Testa o toString() da Sessao

        // 4. CRIAR LOGS DE AUDITORIA
        // Construtor LogAuditoria: (usuario, acao, ip)
        LogAuditoria logAcesso = new LogAuditoria(usuario1, "LOGIN_SUCESSO", "192.168.1.100");
        LogAuditoria logCriacao = new LogAuditoria(usuario1, "CRIACAO_DE_PERFIL", "192.168.1.100");

        System.out.println("\n[ LOGS DE AUDITORIA ]");
        System.out.println(logAcesso); // Testa o toString() do LogAuditoria
        System.out.println(logCriacao); // Testa o toString() do LogAuditoria

        System.out.println("\n--- TESTE CONCLUÍDO ---");

    }
}
