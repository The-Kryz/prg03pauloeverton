/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade09;

/**
 *
 * @author dudan
 */
public class PagamentoPix implements Pagamento {

    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        // Cashback de 2% significa que o cliente paga 98% (100% - 2%) do valor
        final double FATOR_PAGAMENTO = 0.98;

        // Retorna o valor original multiplicado pelo fator (0.98)
        return this.valor * FATOR_PAGAMENTO;
    }

    @Override
    public String imprimirRecibo() {
        final double PERCENTUAL_CASHBACK = 0.02; // 2%
        double valorCashback = this.valor * PERCENTUAL_CASHBACK; // Valor do cashback

        // Monta a string que será exibida no pop-up
        String recibo = "------------------------------------\n"
                + String.format("Tipo: PAGAMENTO COM PIX (2%% Cashback)\n")
                + String.format("Valor Original: R$ %.2f\n", this.valor)
                + String.format("Cashback Recebido: R$ %.2f\n", valorCashback)
                + String.format("VALOR FINAL (98%%): R$ %.2f\n", calcularTotal()) // Chama o método calcularTotal()
                + "------------------------------------";
        return recibo;
    }

}
