/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.modelo;

import java.math.BigDecimal;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Produto {
    private Long id;
    private String dsProduto;
    private Long   cdGrupo;
    private BigDecimal vlCusto;
    private BigDecimal vlProduto;
    private String cdGTIN;
    private String dsPrateleira;
    private String cdNCM;
    private String dsOutrasInfo;
    private boolean inControleEstq;
    private BigDecimal qtEstoque;
    private BigDecimal qtEstoqueMin;
    private BigDecimal vlAtacado;
    private BigDecimal vlPrazo;
    
    
    public String toString(){
        return dsProduto;
    }
}
