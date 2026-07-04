package br.com.qualix.pequenoprincipe.visao;

import java.math.BigDecimal;

/**
 *
 * @author Wagner Almeida
 */
public class Totalizador {
    BigDecimal quantidade = BigDecimal.ZERO;
    BigDecimal valor = BigDecimal.ZERO;

    public void adicionar(BigDecimal qtd, BigDecimal val) {
        this.quantidade = this.quantidade.add(qtd);
        this.valor = this.valor.add(val);
    }

    @Override
    public String toString() {
        return quantidade + " - " + String.format("%.2f", valor);
    }
    
    public BigDecimal getQuantidade(){
        return quantidade;
    }
    
    public BigDecimal getValor(){
        return valor;
    }

}