/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public final class Cubo extends Forma3D {
    private double aresta;

    public Cubo(double aresta) {
        this.aresta = aresta;
    }

    @Override
    public double obterArea() {
        return 6.0 * aresta * aresta; // Fórmula (Área da Superfície): 6 * a²
    }

    @Override
    public double obterVolume() {
        return aresta * aresta * aresta; // Fórmula: a³
    }
    
    @Override
    public String toString() {
        return String.format("Cubo de Aresta %.2f", aresta);
    }
}
