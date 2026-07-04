/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.orcamento.visao;

import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Wagner
 */
public class Apagar {
    
    public static void main(String[] args) {
        String texto = "30/11/2023 23:12:45";
        
        System.out.println(texto.substring(0, 2)); //dia
        System.out.println(texto.substring(3, 5)); //mes
        System.out.println(texto.substring(6, 10)); //ano
        System.out.println(texto.substring(11, 13)); //hora
        System.out.println(texto.substring(14, 16)); //min
        System.out.println(texto.substring(17, 19)); //seg
        
        
        LocalDateTime data = LocalDateTime.of(2023, 11, 30, 0, 3, 1);
    }
}
