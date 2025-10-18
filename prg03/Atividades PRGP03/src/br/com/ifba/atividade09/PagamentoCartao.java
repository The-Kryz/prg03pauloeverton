/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09;

/**
 *
 * @author dudan
 */
public class PagamentoCartao implements Pagamento {

    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {

        double taxa = 0.05;
        return this.valor + (this.valor * taxa);

    }

    @Override
    public String imprimirRecibo() {
        String recibo = "------------------------------------\n"
                + String.format("Tipo: PAGAMENTO COM CARTÃO\n")
                + String.format("Valor Original: R$ %.2f\n", this.valor)
                + String.format("Taxa (5%%): R$ %.2f\n", this.valor * 0.05)
                + String.format("VALOR FINAL: R$ %.2f\n", calcularTotal())
                + "------------------------------------";
        return recibo;
    }

}
