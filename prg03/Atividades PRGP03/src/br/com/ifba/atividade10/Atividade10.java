/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author dudan
 */
public class Atividade10 {
    
    public static void main(String[] args) {
        // 1. Array de referências da classe Forma
        Forma[] formas = new Forma[6]; 

        // 2. Preenche o array com objetos de cada classe concreta
        System.out.println("Criando instâncias das Formas...");
        formas[0] = new Circulo(5.0);
        formas[1] = new Quadrado(4.0);
        formas[2] = new Triangulo(3.0, 6.0); // base=3, altura=6
        formas[3] = new Esfera(3.0);
        formas[4] = new Cubo(2.0);
        // ladoBase=5, altura=8, alturaInclinada=8.5 (Valores de exemplo)
        formas[5] = new Piramide(5.0, 8.0, 8.5); 

        System.out.println("\n--- Processamento das Formas (Polimorfismo) ---");
        
        // 3. Laço que processa todas as formas no array
        for (Forma forma : formas) {
            
            // a) Mostra uma descrição de texto (toString)
            System.out.println("\n--- Objeto ---");
            System.out.println("Descrição: " + forma.toString());

            // b) Verifica o tipo para chamar métodos específicos
            if (forma instanceof Forma2D) {
                // Downcasting seguro para chamar métodos de Forma2D
                Forma2D forma2D = (Forma2D) forma; 
                
                // Se for FormaBidimensional, exibe sua área
                System.out.printf("Tipo: Forma Bidimensional | Área: %.2f\n", 
                                  forma2D.obterArea());
            } 
            else if (forma instanceof Forma3D) {
                // Downcasting seguro para chamar métodos de Forma3D
                Forma3D forma3D = (Forma3D) forma; 
                
                // Se for FormaTridimensional, exibe sua área e seu volume
                System.out.printf("Tipo: Forma Tridimensional | Área: %.2f | Volume: %.2f\n", 
                                  forma3D.obterArea(), 
                                  forma3D.obterVolume());
            }
        }
        System.out.println("\n--- Fim do Processamento ---");
    }
}