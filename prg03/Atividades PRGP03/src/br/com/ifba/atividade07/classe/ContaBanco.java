/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade07.classe;

import javax.swing.JOptionPane;

/**
 *
 * @author dudan
 */
public class ContaBanco {

    public int numConta;
    protected String tipo;
    private String dono;
    private double saldo; // É aqui que o dinheiro da conta fica guardado
    private boolean status; // Indica se a conta está ativa ou inativa

    public ContaBanco() {
        // Construtor padrão (sem argumentos): Garante que a conta inicie com valores zerados/padrão
        this.numConta = 0;
        this.saldo = 0;
        this.status = false;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;

    }

    // Método principal para criar a conta com os dados do usuário
    public void abrirConta(String nome, String conta) {

        setDono(nome);
        setTipo(conta);

        // Uso do .equals() para verificar se o texto é CP, garantindo a comparação correta do conteúdo
        if (getTipo().equals("CP")) {
            setSaldo(150); // Dá um saldo inicial de R$ 150,00 para Poupança
        } else {
            setSaldo(50); // Dá um saldo inicial de R$ 50,00 para Corrente (ou qualquer outro tipo)
        }

        // Gera um número aleatório (1 a 1000) para simular o número da conta
        int numeroAleatorio = (int) (Math.random() * 1000) + 1;
        setNumConta(numeroAleatorio);
        setStatus(true); // A conta já nasce ativa
    }

    // Método que faz a operação de adicionar valor
    public void depositar(double valor) {

        // A CORREÇÃO CRÍTICA: Pega o saldo atual e SOMA o valor depositado
        this.saldo += valor;

    }

    // Método que faz a operação de retirar valor
    public void sacar(double valor) {

        // A CORREÇÃO CRÍTICA: Pega o saldo atual e SUBTRAI o valor sacado
        this.saldo -= valor;
    }

    // Método de pagar mensalidade
    public void mensalidade() {

        // Verifico se a conta está ativa antes de cobrar a mensalidade (Boa Prática)
        if (!this.isStatus()) {
            JOptionPane.showMessageDialog(null, "Conta inativa. Não é possível cobrar mensalidade.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double taxa = 0;

        // 1. Defino a taxa a ser cobrada
        if (getTipo().equals("CP")) {
            taxa = 20;
        } else { // Assumindo que é CC ou outro tipo (12)
            taxa = 12;
        }

        // 2. Verifico o saldo CRITICAMENTE antes de fazer a transação
        if (this.saldo < taxa) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente (R$ " + taxa + ") para pagar a mensalidade.", "Erro de Saldo", JOptionPane.ERROR_MESSAGE);
            return; // SE O SALDO FOR INSUFICIENTE, O MÉTODO TERMINA AQUI.
        }

        // 3. Se passou na verificação, faço a dedução
        this.saldo -= taxa; // Deduz o valor da taxa

        // 4. Mensagem de Sucesso
        JOptionPane.showMessageDialog(null, "Mensalidade de R$ " + taxa + " cobrada com sucesso.", "Transação Completa", JOptionPane.INFORMATION_MESSAGE);
    }

    public void fecharConta() {

        //testa o stuts da conta para nao realizar a operação com a conta inativa
        if (!this.isStatus()) {
            JOptionPane.showMessageDialog(null, "Conta já inativa. Não é possível fechar a conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //condição para checar se a conta possue dinheiro antes de inativar
        if (this.saldo > 0) {
            JOptionPane.showMessageDialog(null, "Conta possue dinheiro, faça o saque do valor total antes de fechar a conta", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, " Conta inativada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.status = false;

        }

    }

}
