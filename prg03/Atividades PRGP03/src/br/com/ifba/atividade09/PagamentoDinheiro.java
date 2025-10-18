/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09;

/**
 *
 * @author dudan
 */
public class PagamentoDinheiro implements Pagamento {

    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        final double FATOR_DESCONTO = 0.90;

        return this.valor * FATOR_DESCONTO;
    }

    @Override
    public String imprimirRecibo() {
        final double PERCENTUAL_DESCONTO = 0.10; // 10%
        double valorDesconto = this.valor * PERCENTUAL_DESCONTO; // Valor do desconto em reais

        // Monta a string que será exibida no pop-up
        String recibo = "------------------------------------\n"
                + String.format("Tipo: PAGAMENTO COM DINHEIRO (10%% Desc.)\n")
                + String.format("Valor Original: R$ %.2f\n", this.valor)
                + String.format("Desconto de R$ %.2f\n", valorDesconto) // Valor do desconto calculado
                + String.format("VALOR FINAL: R$ %.2f\n", calcularTotal()) // Chama o método calcularTotal()
                + "------------------------------------";
        return recibo;
    }

}
