/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.print.servico;

import JNumberField.JNumberField;
import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import br.com.qualix.proserv.modelo.PedidoProduto;
import java.math.BigDecimal;

/**
 *
 * @author Wagner
 */
public class FormataInfoImpressora {
    
    
    public static String formataProduto(PedidoProduto p){
        String dsTexto = "";
        String dsQtde = "";
        String dsDescricao = "";
        String dsValor = "";
        
        
        //Quantidade
        if (p.getQtItem().doubleValue() < 10){
            dsQtde = "  " + p.getQtItem();
        } else if (p.getQtItem().doubleValue() < 100){
            dsQtde = " " + p.getQtItem();
        } else {
            dsQtde = "" + p.getQtItem();
        }
        
                
        
        //Descricao
        if (p.getProduto().getDsProduto() != null){
            dsDescricao = formataDesc(p.getProduto().getDsProduto());
        } else {
            dsDescricao = formataDesc("PRODUTO SEM DESCRICAO");
        }



        
        //Valor
        JNumberField jnf = new JNumberField();
        jnf.setValue(p.getVlItem().multiply(p.getQtItem()));
        String preco = jnf.getText();
        preco = preco.substring(3);
        
        int saldoSpace = 0;
        saldoSpace = 8 - preco.length();
        for (int i = 0; i < saldoSpace; i++) {
            preco = " " + preco;
        }
        
        String retorno = dsQtde + " " + dsDescricao + " " + preco;
        
        if (p.getDsObs() != null && !p.getDsObs().equals("")){
            String obs = " -->" + p.getDsObs();
            obs = quebraEm42(obs);
            retorno += "\n" + obs;
        }
        
        return retorno;
    }
    
    
    public static String formataPagamento(PedidoPagamento p){
        String dsTexto = "";
        String dsQtde = "";
        String dsDescricao = "";
        String dsValor = "";
        
        
        //Descricao
        if (p.getPagamento().getDsTipoPagamento() != null){
            if (p.getPagamento().getDsTipoPagamento().equals("CREDITO")){
                String complem = "";
                if (p.getPagamento().getQt() == 1){
                    //complem += " (em " + p.getPagamento().getQt() + " parcela)";
                } else {
                    complem += " (em " + p.getPagamento().getQt() + " parcelas)";
                }
                
                dsDescricao = formataDesc(p.getPagamento().getDsTipoPagamento() + complem);
            } else {
                dsDescricao = formataDesc(p.getPagamento().getDsTipoPagamento());
            }
        } else {
            dsDescricao = formataDesc("ERRO NA DESCRICAO");
        }



        
        //Valor
        JNumberField jnf = new JNumberField();
        jnf.setValue(p.getVlPagamento());
        String preco = jnf.getText();
        preco = preco.substring(3);
        
        int saldoSpace = 0;
        saldoSpace = 8 - preco.length();
        for (int i = 0; i < saldoSpace; i++) {
            preco = " " + preco;
        }
        
        return dsQtde + " " + dsDescricao + "    " + preco;
    }
    
    
    
    
    
    
    
    
    
//    public static String formataProdutoConsignado(String tpListaPreco, Consignado p){
//        String dsTexto = "";
//        String dsQtde = "";
//        String dsDescricao = "";
//        String dsValor = "";
//        
//        
//        //Quantidade
//        double valor = p.getQtConsignado_ND();
//        int vint = (int) valor;
//        if (vint < 10){
//            dsQtde += "  ";
//        } else if (vint <100){
//            dsQtde += " ";
//        }
//        
//        if (vint == 0){
//            vint += 1;
//        }
//        dsQtde += Integer.toString(vint);
//        
//        
//        //Descricao
//        if (p.getDsConsignado() == null){
//            dsDescricao = p.getDsConsignado();
//        } else if (p.getDsConsignado().equals("     ")){
//            dsDescricao = p.getDsConsignado();
//        } else if (p.getDsConsignado().equals(" ")){
//            dsDescricao = p.getDsConsignado();
//        } else if (p.getDsConsignado().equals("")){
//            dsDescricao = p.getDsConsignado();
//        } else {
//            dsDescricao = p.getDsConsignado();
//        }
//        dsDescricao = formataDesc(dsDescricao);
//        
//        //Valor
//        JNumberField jnf = new JNumberField();
//        jnf.setValue(BigDecimal.valueOf(p.getValorProduto(tpListaPreco) * p.getQtConsignado_ND()));
//        String preco = jnf.getText();
//        preco = preco.substring(3);
//        
//        int saldoSpace = 0;
//        saldoSpace = 8 - preco.length();
//        for (int i = 0; i < saldoSpace; i++) {
//            preco = " " + preco;
//        }
//        
//        if (p.getQtConsignado_ND()!= vint){
//            JNumberField jnfqt = new JNumberField(3);
//            jnfqt.setValue(BigDecimal.valueOf(p.getQtConsignado_ND()));
//            preco += "\n\033M1(qtde prod acima: " + jnfqt.getText() + ")\033M0";
//        }
//        
//        //Texto
//        return dsQtde + " " + dsDescricao + " " + preco;
//    }
    
    
    
    
    
    public static String formataTextoCentraliza(String dsDescricao){
        int tamanhoTexto = (int) dsDescricao.length() / 2;
        int saldoSpace = 0;
        
        
        if (Configuracoes.IMPRESSORA_TAM.equals("58mm")){
            saldoSpace = 16 - tamanhoTexto; //16 é a metade do tamanho do papael
        } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")){
            saldoSpace = 24 - tamanhoTexto; //24 é a metade do tamanho do papael
        } else {
            saldoSpace = 21 - tamanhoTexto; //21 é a metade do tamanho do papael
        }
        
        
        for (int i = 0; i < saldoSpace; i++) {
            dsDescricao = " " + dsDescricao;
        }
        return dsDescricao;
    }
    
    
    
    public static String retiraAcentuacao(String dsDescricao){
        dsDescricao = dsDescricao.replace("Ã", "A");
        dsDescricao = dsDescricao.replace("Á", "A");
        dsDescricao = dsDescricao.replace("À", "A");
        dsDescricao = dsDescricao.replace("Â", "A");
        dsDescricao = dsDescricao.replace("Ä", "A");
        dsDescricao = dsDescricao.replace("ã", "a");
        dsDescricao = dsDescricao.replace("á", "a");
        dsDescricao = dsDescricao.replace("à", "a");
        dsDescricao = dsDescricao.replace("â", "a");
        dsDescricao = dsDescricao.replace("ä", "a");
        dsDescricao = dsDescricao.replace("É", "E");
        dsDescricao = dsDescricao.replace("Ê", "E");
        dsDescricao = dsDescricao.replace("È", "E");
        dsDescricao = dsDescricao.replace("Ë", "E");
        dsDescricao = dsDescricao.replace("é", "e");
        dsDescricao = dsDescricao.replace("ê", "e");
        dsDescricao = dsDescricao.replace("è", "e");
        dsDescricao = dsDescricao.replace("ë", "e");
        dsDescricao = dsDescricao.replace("Í", "I");
        dsDescricao = dsDescricao.replace("Ì", "I");
        dsDescricao = dsDescricao.replace("Î", "I");
        dsDescricao = dsDescricao.replace("Ï", "I");
        dsDescricao = dsDescricao.replace("í", "i");
        dsDescricao = dsDescricao.replace("ì", "i");
        dsDescricao = dsDescricao.replace("î", "i");
        dsDescricao = dsDescricao.replace("ï", "i");
        dsDescricao = dsDescricao.replace("Ó", "O");
        dsDescricao = dsDescricao.replace("Ò", "O");
        dsDescricao = dsDescricao.replace("Õ", "O");
        dsDescricao = dsDescricao.replace("Ô", "O");
        dsDescricao = dsDescricao.replace("Ö", "O");
        dsDescricao = dsDescricao.replace("ó", "o");
        dsDescricao = dsDescricao.replace("ò", "o");
        dsDescricao = dsDescricao.replace("õ", "o");
        dsDescricao = dsDescricao.replace("ô", "o");
        dsDescricao = dsDescricao.replace("ö", "o");
        dsDescricao = dsDescricao.replace("Ú", "U");
        dsDescricao = dsDescricao.replace("Ù", "U");
        dsDescricao = dsDescricao.replace("Û", "U");
        dsDescricao = dsDescricao.replace("Ü", "U");
        dsDescricao = dsDescricao.replace("ú", "u");
        dsDescricao = dsDescricao.replace("ù", "u");
        dsDescricao = dsDescricao.replace("û", "u");
        dsDescricao = dsDescricao.replace("ü", "u");
        dsDescricao = dsDescricao.replace("Ç", "C");    
        dsDescricao = dsDescricao.replace("ç", "c");    
        return dsDescricao;
    }
    
    
    
    
    private static String formataDesc(String dsDescricao){
        
        if (Configuracoes.IMPRESSORA_TAM.equals("58mm")){
            if (dsDescricao.length() > 18){
                dsDescricao = dsDescricao.substring(0, 18);
            }
            
        } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")){
            if (dsDescricao.length() > 33){
                dsDescricao = dsDescricao.substring(0, 36);
            }
            
        } else {
            if (dsDescricao.length() > 28){
                dsDescricao = dsDescricao.substring(0, 28);
            }
            
        }
        
        dsDescricao = dsDescricao.replace("Ã", "A");
        dsDescricao = dsDescricao.replace("Á", "A");
        dsDescricao = dsDescricao.replace("À", "A");
        dsDescricao = dsDescricao.replace("Â", "A");
        dsDescricao = dsDescricao.replace("Ä", "A");
        dsDescricao = dsDescricao.replace("É", "E");
        dsDescricao = dsDescricao.replace("Ê", "E");
        dsDescricao = dsDescricao.replace("È", "E");
        dsDescricao = dsDescricao.replace("Ë", "E");
        dsDescricao = dsDescricao.replace("Í", "I");
        dsDescricao = dsDescricao.replace("Ì", "I");
        dsDescricao = dsDescricao.replace("Î", "I");
        dsDescricao = dsDescricao.replace("Ï", "I");
        dsDescricao = dsDescricao.replace("Ó", "O");
        dsDescricao = dsDescricao.replace("Ò", "O");
        dsDescricao = dsDescricao.replace("Õ", "O");
        dsDescricao = dsDescricao.replace("Ô", "O");
        dsDescricao = dsDescricao.replace("Ö", "O");
        dsDescricao = dsDescricao.replace("Ú", "U");
        dsDescricao = dsDescricao.replace("Ù", "U");
        dsDescricao = dsDescricao.replace("Û", "U");
        dsDescricao = dsDescricao.replace("Ü", "U");
        dsDescricao = dsDescricao.replace("Ç", "C");
        
        int saldoSpace = 0;
        if (Configuracoes.IMPRESSORA_TAM.equals("58mm")){
            saldoSpace = 18 - dsDescricao.length();
        } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")){
            saldoSpace = 35 - dsDescricao.length();
        }else {
            saldoSpace = 28 - dsDescricao.length();
        }
        
        for (int i = 0; i < saldoSpace; i++) {
            dsDescricao += " ";
        }
        
        return dsDescricao;
    }
    
    
    public static String quebraEm42(String dsDescricao){
        
        
        int qt = dsDescricao.length();
        String nova = "";
        
        if (Configuracoes.IMPRESSORA_TAM.equals("58mm")){
            
            //Configurações 58mm (quebra em 32)
            for (int i = 1; i <= qt; i++) {
                nova += dsDescricao.charAt(i-1);
                if (i > 1 && i % 31 == 0){
                    nova += "\n";
                }
            }
        } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")){
            //Configurações 90mm (quebra em 48)
            for (int i = 1; i <= qt; i++) {
                nova += dsDescricao.charAt(i-1);
                if (i > 1 && i % 47 == 0){
                    nova += "\n";
                }
            }
            
        } else {
            
            //Configurações 80mm (quebra em 42)
            for (int i = 1; i <= qt; i++) {
                nova += dsDescricao.charAt(i-1);
                if (i > 1 && i % 41 == 0){
                    nova += "\n";
                }
            }
            
        }
        
        
        
        return nova;
    }
    
        
    
}
