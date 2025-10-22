/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Triangulo extends Forma2D {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double obterArea() {
        return (base * altura) / 2.0; // Fórmula: (base * altura) / 2
    }

    @Override
    public String toString() {
        return String.format("Triângulo de Base %.2f e Altura %.2f", base, altura);
    }
}
