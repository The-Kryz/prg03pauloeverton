/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Piramide extends Forma3D {
    private double ladoBase;
    private double altura;
    private double alturaInclinada; // Necessário para cálculo da área lateral

    public Piramide(double ladoBase, double altura, double alturaInclinada) {
        this.ladoBase = ladoBase;
        this.altura = altura;
        this.alturaInclinada = alturaInclinada;
    }

    @Override
    public double obterArea() {
        // Área Total = Área da Base + Área Lateral
        double areaBase = ladoBase * ladoBase; // Base Quadrada
        double areaLateral = 4 * ( (ladoBase * alturaInclinada) / 2.0 ); // 4 triângulos
        return areaBase + areaLateral;
    }

    @Override
    public double obterVolume() {
        // Fórmula: (Área da Base * altura) / 3
        double areaBase = ladoBase * ladoBase;
        return (areaBase * altura) / 3.0;
    }
    
    @Override
    public String toString() {
        return String.format("Pirâmide de Base Quadrada (Lado %.2f, Altura %.2f)", ladoBase, altura);
    }
}