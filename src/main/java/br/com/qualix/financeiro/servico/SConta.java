/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.servico;

import br.com.qualix.database.dao.service.DaoFactory;
import br.com.qualix.financeiro.modelo.Conta;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
public class SConta {
    
    
    
    public static ArrayList<Conta> gravaContas(ArrayList<Conta> contas) throws Exception{
        for (Conta conta : contas) {
            conta = DaoFactory.getDaoConta().grava(conta);
        }
        return contas;
    }
    
    public static Conta gravaConta(Long id, int nrParcela, int qtParcela, String tpMovimento, String tpSituacao, Long idPessoa, String nmCliConta, LocalDateTime dtTransacao, LocalDate dtVencimento, LocalDate dtPagamento, BigDecimal vlDebito, BigDecimal vlPago, String tpOrigem, Long idOrigem, String dsObs, boolean inExcluido, ArrayList<PedidoPagamento> listaPagamento) throws IOException, Exception{
        Conta conta = new Conta();
        conta.setId(id);
        conta.setNrParcela(nrParcela);
        conta.setQtParcela(qtParcela);
        conta.setTpMovimento(tpMovimento);
        conta.setTpSituacao(tpSituacao);
        conta.setIdPessoa(idPessoa);
        conta.setNmCliConta(nmCliConta);
        if (dtTransacao == null){
            conta.setDtTransacao(LocalDateTime.now());
        }
        conta.setDtVencimento(dtVencimento);
        conta.setDtPagamento(dtPagamento);
        conta.setVlDebito(vlDebito);
        conta.setVlPago(vlPago);
        conta.setTpOrigem(tpOrigem);
        conta.setIdOrigem(idOrigem);
        conta.setDsObs(dsObs);
        conta.setInExcluido(inExcluido);
        conta.setListaPagamento(listaPagamento);
        return DaoFactory.getDaoConta().grava(conta);
    }
    
    public static void deleteConta(Long id) throws Exception{
        Conta conta = new Conta();
        conta.setId(id);
        DaoFactory.getDaoConta().delete(conta);
    }
    
    public static ArrayList<Conta> retrieveConta() throws Exception{
        ArrayList<Conta> listaConta = new ArrayList<>();
        return DaoFactory.getDaoConta().retrieve();
    }
    
    public static Conta retrieveConta(Long id) throws Exception{
        ArrayList<Conta> listaConta = new ArrayList<>();
        listaConta = DaoFactory.getDaoConta().retrieve();
        for (Conta conta : listaConta) {
            if (conta.getId().doubleValue() == id.doubleValue()){
                return conta;
            }
        }
        return null;
    }
}
