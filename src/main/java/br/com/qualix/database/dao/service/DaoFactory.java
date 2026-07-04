/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.database.dao.service;


//#######    BANCO DE DADOS   #######
////import br.com.qualix.financeiro.daoPostgreSQL.*;
//import br.com.qualix.financeiro.dao.*;
//import br.com.qualix.pessoa.daoPostgreSQL.*;
//import br.com.qualix.proserv.daoPostgreSQL.*;

//#######    DADOS EM ARQUIVOS   #######
import br.com.qualix.financeiro.dao.*;
import br.com.qualix.pessoa.dao.*;
import br.com.qualix.proserv.dao.*;



public class DaoFactory {

    private static DPessoa  daoPessoa;
    private static DGrupo   daoGrupo;
    private static DProduto daoProduto;
    private static DPedido  daoPedido;
    private static DConta   daoConta;
    
    
    public static DPessoa getDaoPessoa(){
        if (daoPessoa == null){
            if (true){
                daoPessoa = new DPessoa();
            }
        }
        return daoPessoa;
    }
    
    public static DGrupo getDaoGrupo(){
        if (daoGrupo == null){
            daoGrupo = new DGrupo();
        }
        return daoGrupo;
    }
    
    public static DProduto getDaoProduto(){
        if (daoProduto == null){
            daoProduto = new DProduto();
        }
        return daoProduto;
    }
    
    public static DPedido getDaoPedido(){
        if (daoPedido == null){
            daoPedido = new DPedido();
        }
        return daoPedido;
    }
    
    public static DConta getDaoConta(){
        if (daoConta == null){
            daoConta = new DConta();
        }
        return daoConta;
    }
            
            
            
}
