/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.servico;

//import br.com.qualix.proserv.daoPostgreSQL.DProduto;
import br.com.qualix.database.dao.service.DaoFactory;
import br.com.qualix.proserv.modelo.Produto;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
public class SProduto {
    //private static DProduto dproduto = new DProduto();
    
    public static void gravaProduto(Long id, String dsProduto, Long cdGrupo, BigDecimal vlCusto, BigDecimal vlProduto, String cdGTIN, String dsPrateleira, String cdNCM, String dsOutrasInfo, boolean inControleEstq, BigDecimal qtEstoque, BigDecimal qtEstoqueMin, BigDecimal vlAtacado, BigDecimal vlPrazo) throws IOException, Exception{
        Produto produto = new Produto();
        produto.setId(id);
        produto.setDsProduto(dsProduto);
        produto.setCdGrupo(cdGrupo);
        produto.setVlCusto(vlCusto);
        produto.setVlProduto(vlProduto);
        produto.setCdGTIN(cdGTIN);
        produto.setDsPrateleira(dsPrateleira);
        produto.setCdNCM(cdNCM);
        produto.setDsOutrasInfo(dsOutrasInfo);
        produto.setInControleEstq(inControleEstq);
        produto.setQtEstoque(qtEstoque);
        produto.setQtEstoqueMin(qtEstoqueMin);
        produto.setVlAtacado(vlAtacado);
        produto.setVlPrazo(vlPrazo);
//        dproduto.grava(produto);
        DaoFactory.getDaoProduto().grava(produto);
    }
    
    
    public static void gravaProduto(Produto produto) throws Exception{
        DaoFactory.getDaoProduto().grava(produto);
    }
    
    
    public static void deleteProduto(Long id) throws IOException, Exception{
        Produto produto = new Produto();
        produto.setId(id);
//        dproduto.delete(produto);
        DaoFactory.getDaoProduto().delete(produto);
    }
    
    public static ArrayList<Produto> retrieveProduto() throws IOException, Exception{
        ArrayList<Produto> listaProduto = new ArrayList<>();
//        return dproduto.retrieve();
        return DaoFactory.getDaoProduto().retrieve();
    }
    
    public static Produto retrieveProduto(Long id) throws IOException, Exception{
        ArrayList<Produto> listaProduto = new ArrayList<>();
//        listaProduto = dproduto.retrieve();
        listaProduto = DaoFactory.getDaoProduto().retrieve();
        for (Produto produto : listaProduto) {
            if (produto.getId().doubleValue() == id.doubleValue()){
                return produto;
            }
        }
        return null;
    }
    
    
    //mudar, se banco de dados pegar busca pelo SQL
    public static Produto retrieveProdutoPorGTIN(String gtin) throws IOException, Exception{
        ArrayList<Produto> listaProduto = new ArrayList<>();
        listaProduto = DaoFactory.getDaoProduto().retrieve();
        for (Produto produto : listaProduto) {
            if (produto.getCdGTIN() != null && produto.getCdGTIN().equals(gtin)){
                return produto;
            }
        }
        return null;
    }
    
    public static ArrayList<Produto> retrieveProdutosPorGTIN(String gtin) throws IOException, Exception{
        ArrayList<Produto> listaProduto = new ArrayList<>();
        ArrayList<Produto> listaNovaProduto = new ArrayList<>();
        listaProduto = DaoFactory.getDaoProduto().retrieve();
        String nome = "";
        for (Produto produto : listaProduto) {
            String gt = produto.getCdGTIN();
            if (gt != null && gt.equals(gtin)){
                listaNovaProduto.add(produto);
            }
        }
        return listaNovaProduto;
    }
    
    
    
    
    
    
}
