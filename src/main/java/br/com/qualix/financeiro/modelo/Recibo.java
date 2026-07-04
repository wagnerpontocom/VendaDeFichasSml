/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Recibo {
    private Long id;
    private Long idPessoa;
    private LocalDateTime dtTransacao;
    private BigDecimal vlPagamento;
    private String dsObs;
    private boolean inCancelado;
    
    private ArrayList<ReciboPagamento> pagamentos;
}
