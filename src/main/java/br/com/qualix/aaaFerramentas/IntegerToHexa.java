/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaFerramentas;

import java.awt.Color;

/**
 *
 * @author Wagner
 */
public class IntegerToHexa {
    
    
    
    static final String digits = "0123456789ABCDEF";
    static String integerToHex(int input) {
        if (input <= 0)
            return "0";
        StringBuilder hex = new StringBuilder();
        while (input > 0) {
            int digit = input % 16;
            hex.insert(0, digits.charAt(digit));
            input = input / 16;
        }
        return hex.toString();
    }
    
    public static String rgbToCode(int pr, int pg, int pb){
        String r = integerToHex(pr);
        if (r.length() == 1){
            r = "0"+r;
        }
        String g = integerToHex(pg);
        if (g.length() == 1){
            g = "0"+g;
        }
        String b = integerToHex(pb);
        if (b.length() == 1){
            b = "0"+b;
        }
        return "#"+r+g+b;
    }
    
    
    public static Color rgbCodeToColor(String dsCor){
        
        try {
            System.out.println("a");
            if (!dsCor.substring(0,1).equals("#")){
                throw new Exception("erro");
            }
            
            int r = Integer.parseInt(dsCor.substring(1,3), 16);
            int g = Integer.parseInt(dsCor.substring(3,5), 16);
            int b = Integer.parseInt(dsCor.substring(5,7), 16);
        
            Color cor = new Color(r, g, b);
            return cor;
        } catch (Exception e) {
            return null;
        }
        
    }
    
            
}
