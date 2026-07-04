/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.modelo;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
public class TipoPagamento {
    private Long id;
    private String dsTipoPagamento;
    private int qt;
    
    
    public static ArrayList<TipoPagamento> retornaTipoPagamento(){
        ArrayList<TipoPagamento> lista = new ArrayList<>();
        TipoPagamento tipo;
        
        tipo = new TipoPagamento(1l, "DINHEIRO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(2l, "CREDITO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(3l, "DEBITO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(4l, "PIX", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(6l, "CREDIARIO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(7l, "BOLETO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(8l, "CHEQUE", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(9l, "DESCONTO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(10l, "TROCO", 0);
        lista.add(tipo);
        
        tipo = new TipoPagamento(11l, "BRINDE", 0);
        lista.add(tipo);
        
        return lista;
    }
    
    public String toString(){
        if (id == 2L && qt > 1){
            return dsTipoPagamento + " (" + qt + " x)";
        }
        return dsTipoPagamento;
    }
}
