/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Esfera extends Forma3D {

    private double raio;

    public Esfera(double raio) {
        this.raio = raio;
    }

    @Override
    public double obterArea() {
        return 4.0 * Math.PI * raio * raio; // Fórmula (Área da Superfície): 4 * π * r²
    }

    @Override
    public double obterVolume() {
        return (4.0 / 3.0) * Math.PI * raio * raio * raio; // Fórmula: (4/3) * π * r³
    }

    @Override
    public String toString() {
        return String.format("Esfera de Raio %.2f", raio);
    }
}
