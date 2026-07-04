/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.modelo;

import java.math.BigDecimal;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ReciboPagamento {
    private Long id;
    private TipoPagamento pagamento;
    private BigDecimal vlPagamento;
    
    
    public String toString(){
        return pagamento.getDsTipoPagamento();
    }
}
