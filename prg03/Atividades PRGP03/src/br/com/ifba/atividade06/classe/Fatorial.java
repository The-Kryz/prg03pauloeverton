/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade06.classe;

import javax.swing.text.Utilities;

/**
 *
 * @author dudan
 */
public class Fatorial {
    
    int setar; // variavel a receber o valor do txtvalor
    
    public void setValor (int n){
        setar = n; //metodo para setar o valor 
    }
    
    public int getFatorial (){
        
        if (setar < 0){
            return -1;
        }
        if (setar == 0 || setar == 1){
            return 1;
        }
        
        int res = 1;
        
        // o que importa é isso aqui, calcula o valor do fatorial fazendo um for com o valor atribuido em setar
        for (int i = 2; i <= setar; i++){
            res *= i;
        }
        return res;
    }
    
    public String getFormula (){

        if (setar < 0){
            return "Valor invalido";
        }
        
        if (setar == 0 || setar == 1){
            return "! = 1";
        }
        
        //cria o objeto StringBuilder < string 
        StringBuilder formula = new StringBuilder();
        
        // une, soma ou coloca na frente (chama como quiser), o valor na variavel setar mais ! =. ex: 5! =
        formula.append(setar).append("! =");
        
        //append é muito bom, ele coloca na frente o valor atribuido a ele. ex 5 x 4 x 3
        //aqui faz a repetição para ir colocando o valor de setar + -1 formando a Sting da fatoração
        for (int i = setar; i >= 1; i--){
            formula.append(i);
            if (i > 1){
                formula.append(" x ");
            }
        }
        //retorna o append completo
        return formula.toString();
    }
}
