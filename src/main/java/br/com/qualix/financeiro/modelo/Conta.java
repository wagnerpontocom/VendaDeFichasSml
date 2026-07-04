/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.modelo;

import JNumberField.JNumberField;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class Conta {
    private Long id;
    private int nrParcela;
    private int qtParcela;
    private String tpMovimento; //Entrada - Saída
    private String tpSituacao; //Aberto, Pago, Parcial, Cancelada
    private Long idPessoa;
    private String nmCliConta;
    private LocalDateTime dtTransacao;
    private LocalDate dtVencimento;
    private LocalDate dtPagamento;
    private BigDecimal vlDebito;
    private BigDecimal vlPago;
    
    private String tpOrigem; //Manual, Contas Receber, Contas Pagar, Pedido
    private Long idOrigem;
    private String dsObs;
    private boolean inExcluido;
    
    ArrayList<PedidoPagamento> listaPagamento = new ArrayList<>();
    
    
    public static String TIPO_MOVIMENTO_ENTRADA = "Entrada";
    public static String TIPO_MOVIMENTO_SAIDA   = "Saida";
    
    public static String TIPO_SITUACAO_ABERTO    = "Aberto";
    public static String TIPO_SITUACAO_PAGO      = "Pago";
    public static String TIPO_SITUACAO_PARCIAL   = "Parcial";
    public static String TIPO_SITUACAO_CANCELADA = "Cancelada";
    
    public static String TIPO_ORIGEM_MANUAL         = "Manual";
    public static String TIPO_ORIGEM_CONTAS_RECEBER = "Contas Receber";
    public static String TIPO_ORIGEM_CONTAS_PAGAR   = "Contas Pagar";
    public static String TIPO_ORIGEM_PEDIDO         = "Pedido/Venda";
    
    
    
    private BigDecimal getSaldo(){
        if (vlPago != null){
            return vlDebito.subtract(vlPago);
        } else {
            return vlDebito;
        }
    }
    
    public String toString(){
        JNumberField jnf = new JNumberField();
        jnf.setValue(getSaldo());
        return nmCliConta + " (" + jnf.getText() + ")";
    }
}
