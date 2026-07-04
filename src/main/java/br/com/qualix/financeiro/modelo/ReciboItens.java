/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ReciboItens {
    private Long id;
    private Long idRecibo;
    private Long idConta;
    private LocalDate dtVencimento;
    private BigDecimal vlSaldoAnterior;
    private BigDecimal vlPagamento;
    private BigDecimal vlNovoSaldo;
    private boolean inCancelado;
    
}
