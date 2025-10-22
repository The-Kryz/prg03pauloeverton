/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Quadrado extends Forma2D {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double obterArea() {
        return lado * lado; // Fórmula: lado²
    }

    @Override
    public String toString() {
        return String.format("Quadrado de Lado %.2f", lado);
    }
}
