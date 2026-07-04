/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class Pedido {
    private Long id;
    private String nmCliPedido;
    private String tpPedido;
    //private String tpSituacaoAnterior;
    private String tpSituacao;
    private LocalDateTime dtTransacao;
    private LocalDate dtValidade;
    private Long cdPessoa;
    private Long cdVendedor;
    private BigDecimal vlPedido;
    private BigDecimal vlDesconto;
    private BigDecimal vlTotal;
    private boolean inPago;
    private boolean inRetirado;
    private String dsObs;
    private String dsEntrega;
    private boolean inExcluido;
    private boolean inOcultarCusto;
    private String dsObra;
    
    ArrayList<PedidoProduto> listaProduto = new ArrayList<>();
    ArrayList<PedidoPagamento> listaPagamento = new ArrayList<>();
    
    //Pensar em algo parecido com isso aqui
    //ArrayList<PedidoProduto> listaBaixaEstoque = new ArrayList<>();
    
    
    public String toString(){
        return nmCliPedido;
    }
}
