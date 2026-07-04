/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.modelo;

import java.math.BigDecimal;
import java.math.BigInteger;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class PedidoProduto {
    private Long id;
    private Long idPedido;
    private Produto produto;
    private BigDecimal qtItem;
    private BigDecimal vlItem;
    private String dsObs;
    private BigDecimal qtItemBaixa;
    
    private BigDecimal ndb_vlTotal;
    
    
    public String toString(){
        return produto.getDsProduto();
    }
    
    public BigDecimal getNdb_vlTotal(){
        return vlItem.multiply(qtItem);
    }
    
}
