/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Circulo extends Forma2D {

    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double obterArea() {
        return Math.PI * raio * raio; // Fórmula: π * r²
    }

    @Override
    public String toString() {
        return String.format("Círculo de Raio %.2f", raio);
    }
}
