/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.servico;

import JNumberField.JNumberField;
import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.database.dao.service.DaoFactory;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import br.com.qualix.print.modelo.CapsulePrint;
import br.com.qualix.print.servico.FormataInfoImpressora;
import br.com.qualix.print.servico.PP2;
import static br.com.qualix.print.servico.PP2.acionarGuilhotina;
import static br.com.qualix.print.servico.PP2.detectaImpressoras;
import static br.com.qualix.print.servico.PP2.imprime;
import br.com.qualix.proserv.dto.DTO_Pedido;
import br.com.qualix.proserv.modelo.Pedido;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import br.com.qualix.proserv.modelo.PedidoProduto;
import br.com.qualix.proserv.modelo.Produto;
import br.com.qualix.relatorios.servico.Reports;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Wagner
 */
public class SPedido {
    //private static DPedido dpedido = new DPedido();
    
    public static Pedido gravaPedido(Long id, String nmClientePedido, String tpPedido, String tpSituacao, LocalDateTime dtTransacao, LocalDate dtValidade, Long cdPessoa, Long cdVendedor, BigDecimal vlPedido, BigDecimal vlDesconto, BigDecimal vlTotal, boolean inPago, boolean inRetirado, String dsObs, boolean inExcluido, ArrayList<PedidoProduto> listaProdutos, ArrayList<PedidoPagamento> listaPagamentos, String dsEntrega, boolean inOcultaPedido, String dsObra) throws IOException, Exception{
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setNmCliPedido(nmClientePedido);
        pedido.setTpPedido(tpPedido);
        pedido.setTpSituacao(tpSituacao);
        pedido.setDtTransacao(dtTransacao);
        pedido.setDtValidade(dtValidade);
        pedido.setCdPessoa(cdPessoa);
        pedido.setCdVendedor(cdVendedor);
        pedido.setVlPedido(vlPedido);
        pedido.setVlDesconto(vlDesconto);
        pedido.setVlTotal(vlTotal);
        pedido.setInPago(inPago);
        pedido.setInRetirado(inRetirado);
        pedido.setDsObs(dsObs);
        pedido.setInExcluido(inExcluido);
        pedido.setListaProduto(listaProdutos);
        pedido.setListaPagamento(listaPagamentos);
        pedido.setDsEntrega(dsEntrega);
        pedido.setInOcultarCusto(inOcultaPedido);
        pedido.setDsObra(dsObra);
//        return dpedido.grava(pedido);

        if (Configuracoes.PRODUTOS_IN_CONT_ETQ){
            baixaEstoque(pedido);
        }


        return DaoFactory.getDaoPedido().grava(pedido);
    }
    
    public static void deletePedido(Long id) throws IOException, Exception{
        Pedido pedido = new Pedido();
        pedido = SPedido.retrievePedido(id);
        
        if (Configuracoes.PRODUTOS_IN_CONT_ETQ){
            //A situação CANCELADO, faz estornar os produtos
            pedido.setTpSituacao("CANCELADO");
            SPedido.grava(pedido);
        }
        DaoFactory.getDaoPedido().delete(pedido);
    }
    
    public static ArrayList<Pedido> retrievePedido() throws IOException, Exception{
        ArrayList<Pedido> listaPedido = new ArrayList<>();
//        return dpedido.retrieve();
        return DaoFactory.getDaoPedido().retrieve();
    }
    
    public static Pedido retrievePedido(Long id) throws IOException, Exception{
        ArrayList<Pedido> listaPedido = new ArrayList<>();
//        listaPedido = dpedido.retrieve();
        listaPedido = DaoFactory.getDaoPedido().retrieve();
        for (Pedido pedido : listaPedido) {
            if (pedido.getId().doubleValue() == id.doubleValue()){
                return pedido;
            }
        }
        return null;
    }
    
    public static void imprimir(Pedido ped) throws Exception{
        
        Reports rp = new Reports();
        Map map = new HashMap();
       
        if (ped.getTpPedido().equals("ORÇAMENTO")){
            map.put("assinaturas", "true");
        }
        map.put("tpSituacao", ped.getTpSituacao());
        
        
        map.put("id", ped.getId());
        String nmCliente = "";
        if (ped.getCdPessoa() != null){
            nmCliente += ped.getCdPessoa() + "-"; 
        }
        nmCliente += ped.getNmCliPedido();
        map.put("nome", nmCliente);
        String tpCPF = "CPF";
        if (ped.getDtValidade() != null){
            map.put("dtValidade", Pandora.LD_STR(ped.getDtValidade()));
        }
        
        
        
        
        if (ped.getDtTransacao() != null){
            map.put("dtEmissao", Pandora.LD_STR(ped.getDtTransacao().toLocalDate()));
            String hora = "";
            if (ped.getDtTransacao().toLocalTime().getHour() < 10){
                hora += "0";
            }
            hora += ped.getDtTransacao().toLocalTime().getHour() + ":";
            if (ped.getDtTransacao().toLocalTime().getMinute() < 10){
                hora += "0";
            }
            hora += ped.getDtTransacao().toLocalTime().getMinute();
            map.put("dtHora", hora);
        }
        
        String infoQR = "";
        
        //Dados Empresa
        try {
            Pessoa pessoa = SPessoa.retrievePessoa(1l);
            map.put("nomeEmpresa", pessoa.getNmPessoa());
            if (pessoa.getTpPessoa().equals("Pessoa Física")){
                map.put("cnpjEmpresa", "CPF: " + pessoa.getNrCPFCNPJ());
            } else {
                map.put("cnpjEmpresa", "CNPJ: " + pessoa.getNrCPFCNPJ());
            }
            map.put("enderecoEmpresa", pessoa.getDsEndereco());
            String dsEmpree = pessoa.getDsEndereco();
            if (pessoa.getDsCidade() != null){
                dsEmpree = pessoa.getDsEndereco() + " - " + pessoa.getDsCidade() + " " + pessoa.getDsUF();
            }
            map.put("enderecoEmpresaX", dsEmpree);
            
            map.put("cidadeEmpresa", pessoa.getDsCidade() + " - " + pessoa.getDsUF());
            map.put("telefoneEmpresa", "Telefone: " + pessoa.getNrTelefone1());
            if (pessoa.getDsEmail() != null){
                map.put("emailEmpresa", "E-mail: " + pessoa.getDsEmail());
            }
            
            if (pessoa.getNrTelefone1() != null && !pessoa.getNrTelefone1().equals("")){
                infoQR += pessoa.getNrTelefone1();
            } else {
                infoQR += pessoa.getNmPessoa();
            }
            //map.put("obsEmpresa", "        CFTV - Informática - Redes - Alarmes - PABX - Interfones - Energia Solar");
            map.put("obsEmpresa", null);
            map.put("infoQR", infoQR);

        } catch (Exception e) {
            
        }
        
        try {
            Pessoa pessoa = SPessoa.retrievePessoa(ped.getCdPessoa());
            if (pessoa.getTpPessoa().equals("Pessoa Física")){
                tpCPF = "CPF";
            } else {
                tpCPF = "CNPJ";
            }
            map.put("CPF", pessoa.getNrCPFCNPJ());
            map.put("endereco", pessoa.getDsEndereco());
            if (pessoa.getNrTelefone1() != null){
                map.put("telefone", pessoa.getNrTelefone1());
            }
            if (pessoa.getDsEmail() != null){
                map.put("email", pessoa.getDsEmail());
            }
        } catch (Exception e) {
            
        }
        
        map.put("tpCPF", tpCPF + ":");
        
       map.put("tpPedido", ped.getTpPedido());
       //map.put("tpPedido", ped.getTpPedido().substring(0, 1) + ped.getTpPedido().substring(1).toLowerCase());
        
        if (!ped.getDsObs().equals("")){
            map.put("dsObs", ped.getDsObs());
        }
        if (ped.getDsEntrega() != null && !ped.getDsEntrega().equals("")){
            map.put("dsEntrega", ped.getDsEntrega());
        }
        
        
        String dsPagamento = "";
        JNumberField jnf = new JNumberField();
        for (PedidoPagamento pedidoPagamento : ped.getListaPagamento()) {
            jnf.setValue(pedidoPagamento.getVlPagamento());
            if (pedidoPagamento.getPagamento().getId() == 2L){
                dsPagamento += pedidoPagamento.getPagamento().getDsTipoPagamento();
                dsPagamento += " ";
                dsPagamento += jnf.getText();
                if (pedidoPagamento.getPagamento().getQt() > 1){
                    dsPagamento += " (em " + pedidoPagamento.getPagamento().getQt() + " parcelas)";
                }
                dsPagamento += "\n";
            } else {
                dsPagamento += pedidoPagamento.getPagamento().getDsTipoPagamento();
                dsPagamento += " ";
                dsPagamento += jnf.getText();
                dsPagamento += "\n";
            }
            
        }
        if (ped.getVlDesconto().doubleValue() > 0){
            jnf.setValue(ped.getVlDesconto());
            dsPagamento += "DESCONTO " + jnf.getText() + "\n";
        }
        
        if (!dsPagamento.equals("")){
            map.put("dsPagamento", dsPagamento);
        }
        
        BigDecimal somaProd = BigDecimal.ZERO;
        ArrayList<PedidoProduto> listaProdutoRem = new ArrayList<>();
        for (PedidoProduto pedidoProduto : ped.getListaProduto()) {
            if (pedidoProduto.getQtItem() == null){
                listaProdutoRem.add(pedidoProduto);
            } else if (pedidoProduto.getQtItem().doubleValue() == 0){
                listaProdutoRem.add(pedidoProduto);
            } else {
                somaProd = somaProd.add(pedidoProduto.getVlItem().multiply(pedidoProduto.getQtItem()));
            }
        }
        ped.getListaProduto().removeAll(listaProdutoRem); //Remove os zero (baixa estoque)
        
        jnf.setValue(somaProd);
        map.put("vlTotalItens", jnf.getText());
        
        ArrayList<Object> lista = new ArrayList<>();
        lista.addAll(ped.getListaProduto());
        //rp.print(lista, map, "reportTeste");
        
        Image image = null;
        try {
            File sourceimage = new File("C:/Qualix/sistema/small/logo.png");
            image = ImageIO.read(sourceimage);
            map.put("logo", image);
        } catch (Exception e) {
            SPedido temp = new SPedido();
//            Icon imagem = new ImageIcon(temp.getClass().getResource("/assets/logo/logoDefault1.png"));
            
            
            image = new ImageIcon(temp.getClass().getResource("/assets/logo/logoDefault2.png")).getImage();
            map.put("logo", image);
        }
        
        //ImageIcon imgLogo = new ImageIcon("C:/Qualix/sistema/small/logo.jpg");
        //map.put("logo", imgLogo.getImage());
        
        
        CapsulePrint capsule = new CapsulePrint();
        if (ped.isInOcultarCusto()){
            capsule.setDsCaminho("reportPedidoSemCusto");
        } else {
            capsule.setDsCaminho("reportPedido");
        }
        capsule.setLista(lista);
        capsule.setParams(map);
        PP2.imprimePedido(capsule, ped.getId());
        
        
    }
    
    public static void grava(Pedido pedido) throws Exception{
        if (Configuracoes.PRODUTOS_IN_CONT_ETQ){
            baixaEstoque(pedido);
        }
        DaoFactory.getDaoPedido().grava(pedido);
    }
    
//    public static void imprimir(Pedido ped) throws JRException{
//        
//        Reports rp = new Reports();
//        Map map = new HashMap();
//        map.put("nome", "wagner");
//
//        ArrayList<Object> lista = new ArrayList<>();
//        lista.add(ped);
//        rp.print(lista, map, "reportTeste");
//        
//    }
    
    

    
    public static void relatorioPedidos(String tipoPedido, ArrayList<Map> lista, String dsVlTotal) throws Exception{
        JNumberField jnf = new JNumberField();
        Reports rp = new Reports();
        Map map = new HashMap();
       
        //map.put("tpPedido", tipoPedido);
        map.put("tpPedido", tipoPedido.substring(0, 1) + tipoPedido.substring(1).toLowerCase());
        map.put("vlTotalItens", tipoPedido);
        map.put("vlTotalItens", dsVlTotal);
        
        map.put("dtEmissao", Pandora.LD_STR(LocalDate.now()));
        String hora = "";
        if (LocalTime.now().getHour() < 10){
            hora += "0";
        }
        hora += LocalTime.now().getHour() + ":";
        if (LocalTime.now().getMinute() < 10){
            hora += "0";
        }
        hora += LocalTime.now().getMinute();
        map.put("dtHora", hora);
        
        
        String infoQR = "";
        
        //Dados Empresa
        try {
            Pessoa pessoa = SPessoa.retrievePessoa(1l);
            map.put("nomeEmpresa", pessoa.getNmPessoa());
            if (pessoa.getTpPessoa().equals("Pessoa Física")){
                map.put("cnpjEmpresa", "CPF: " + pessoa.getNrCPFCNPJ());
            } else {
                map.put("cnpjEmpresa", "CNPJ: " + pessoa.getNrCPFCNPJ());
            }
            map.put("enderecoEmpresa", pessoa.getDsEndereco());
            String dsEmpree = pessoa.getDsEndereco();
            if (pessoa.getDsCidade() != null){
                dsEmpree = pessoa.getDsEndereco() + " - " + pessoa.getDsCidade() + " " + pessoa.getDsUF();
            }
            map.put("enderecoEmpresaX", dsEmpree);
            
            map.put("cidadeEmpresa", pessoa.getDsCidade() + " - " + pessoa.getDsUF());
            map.put("telefoneEmpresa", "Telefone: " + pessoa.getNrTelefone1());
            if (pessoa.getDsEmail() != null){
                map.put("emailEmpresa", "E-mail: " + pessoa.getDsEmail());
            }
            
            if (pessoa.getNrTelefone1() != null && !pessoa.getNrTelefone1().equals("")){
                infoQR += pessoa.getNrTelefone1();
            } else {
                infoQR += pessoa.getNmPessoa();
            }
            map.put("infoQR", infoQR);

        } catch (Exception e) {
            
        }
        
        
        //rp.print(lista, map, "reportTeste");
        
        Image image = null;
        try {
            File sourceimage = new File("C:/Qualix/sistema/small/logo.png");
            image = ImageIO.read(sourceimage);
            map.put("logo", image);
        } catch (Exception e) {
            br.com.qualix.orcamento.servico.SPedido temp = new br.com.qualix.orcamento.servico.SPedido();
            image = new ImageIcon(temp.getClass().getResource("/assets/logo/logoDefault2.png")).getImage();
            map.put("logo", image);
        }

        ArrayList<DTO_Pedido> listaTemp = new ArrayList<>();
        for (Map m : lista) {
            DTO_Pedido ob = new DTO_Pedido();
            ob.setCodigo(m.get("codigo").toString());
            ob.setNome(m.get("nome").toString());
            ob.setDsObs(m.get("dsObs").toString());
            ob.setSituacao(m.get("situacao").toString());
            ob.setData(m.get("data").toString());
            ob.setValor(m.get("valor").toString());
            listaTemp.add(ob);
        }
        
        CapsulePrint capsule = new CapsulePrint();
        capsule.setDsCaminho("reportVendas");
        capsule.setLista(listaTemp);
        capsule.setParams(map);
        PP2.imprimeRelatorioPedido(capsule);
        
    }

    private static void baixaEstoque(Pedido pedido) throws Exception {
        boolean inAlterado = false;
        boolean inCancelado = pedido.getTpSituacao().equals("CANCELADO");
        
//        boolean inBaixa = false;
//        if (pedido.getTpPedido().equals("VENDA") || pedido.getTpPedido().equals("ORDEM DE SERVIÇO") || pedido.getTpPedido().equals("VENDA")){
//            inBaixa = true;
//        }
        
        for (PedidoProduto pedidoProduto : pedido.getListaProduto()) {
            if (pedidoProduto.getProduto().isInControleEstq()){
                if (pedidoProduto.getQtItemBaixa() == null){
                    pedidoProduto.setQtItemBaixa(BigDecimal.ZERO);
                }
                if (pedidoProduto.getQtItem().doubleValue() != pedidoProduto.getQtItemBaixa().doubleValue() || inCancelado){
                    //deve fazer baixa
                    BigDecimal quantidade = BigDecimal.ZERO;
                    if (inCancelado){
                        if (pedidoProduto.getQtItemBaixa().doubleValue() > 0){
                            quantidade = pedidoProduto.getQtItem().multiply(BigDecimal.valueOf(-1));
                        }
                    } else {
                        quantidade = pedidoProduto.getQtItem().subtract(pedidoProduto.getQtItemBaixa());
                    }

                    
                    
                    //Baixa no item
                    Produto produto = new Produto();
                    produto = SProduto.retrieveProduto(pedidoProduto.getProduto().getId());
                    
                    if (pedido.getTpPedido().equals("COMPRA")){
                        produto.setQtEstoque(produto.getQtEstoque().subtract(quantidade.multiply(BigDecimal.valueOf(-1))));
                    } else {
                        produto.setQtEstoque(produto.getQtEstoque().subtract(quantidade));
                    }
                    
                    
                    SProduto.gravaProduto(produto);
                    //Log

                    //Atualiza o saldo pedido
                    if (inCancelado){
                        pedidoProduto.setQtItemBaixa(BigDecimal.ZERO);
                    } else {
                        pedidoProduto.setQtItemBaixa(pedidoProduto.getQtItemBaixa().add(quantidade));
                    }
                    inAlterado = true;
                }
            }
        }

        if (inAlterado){
//            pedido.setTpSituacaoAnterior(pedido.getTpSituacao());
            DaoFactory.getDaoPedido().grava(pedido);
        }
            
        
    }
    
    
            
    
    public static void imprimeJunino(String codigo, String textoPar) {

        String texto = "";
        try {
            texto += (char)27 + "!" + (char)32;
            texto += "       Barraca";
            texto += (char)27 + "!" + (char)48 + "";
            texto += "\n  PEQUENO PRINCIPE";
            texto += "\n";
            texto += (char)27 + "!" + (char)0 + "";
            texto += FormataInfoImpressora.formataTextoCentraliza("Festa Junina Engenheiro Beltrao");
        } catch (Exception e) {
        }

        try {
            detectaImpressoras(Configuracoes.IMPRESSORA);
        } catch (Exception e) {
            Pandora.msgException(e);
        }
        texto += "\n\n";
        texto += "Ficha de Produtos";
        texto += "\n\n";
        texto += "\033!1 " + textoPar + " \n"; //Maior

        texto += "\n";
        texto += (char)27 + "!" + (char)0 + "";
        texto += "Valido somente para a festa de 2025\n";
        

//    texto += (char)27 + "!" + (char)16 + "Texto largura dupla\n";
//    texto += (char)27 + "!" + (char)32 + "Texto altura dupla\n";


        texto += "\n";
        if (!codigo.equals("")) {
            texto += codigoBarrasSimples(codigo);
//            texto += codigoQR(codigo);
//            texto += codigoQR2("Wagner de Almeida Silveira");
        }

        //texto += "\033M1AGRADECEMOS A PREFERENCIA\n"; //Legal pequeninho
        //texto += "AGRADECEMOS A PREFERENCIA\n";
        texto += "\n\n\n";

        imprime(texto);
        acionarGuilhotina();

    }

    private static String codigoBarrasSimples(String texto8Dig) {
        StringBuilder texto2 = new StringBuilder();
        texto2.append((char) 27).append("@");
        texto2.append((char) 27).append('a').append((char) 1); // ESC a 1 → Centraliza
        texto2.append((char) 29).append("H").append((char) 2);
        texto2.append((char) 29).append("h").append((char) 50);
        texto2.append((char) 29).append("w").append((char) 3);
        texto2.append((char) 29).append("k").append((char) 73);
        texto2.append((char) 8); // 8 caracteres
        texto2.append(texto8Dig); // conteúdo do código
        return texto2.toString();
    }

    private static String codigoQR(String dados) {
        StringBuilder qr = new StringBuilder();

        qr.append((char) 27).append("@");
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0).append((char) 49).append((char) 67).append((char) 6);
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0).append((char) 49).append((char) 69).append((char) 49);
        int len = dados.length() + 3;
        qr.append((char) 29).append("(k");
        qr.append((char) (len % 256)); // pL
        qr.append((char) (len / 256)); // pH
        qr.append((char) 49).append((char) 80).append((char) 48);
        qr.append(dados);
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0).append((char) 49).append((char) 81).append((char) 48);
        qr.append("\n\n");
        return qr.toString();
    }

    private static String codigoQR2(String dados) {
        StringBuilder qr = new StringBuilder();

        // Inicializa a impressora
        qr.append((char) 27).append("@");

        // Define o tamanho do módulo (de 1 a 16)
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0)
                .append((char) 49).append((char) 67).append((char) 4); // 4 = tamanho médio

        // Define o nível de correção de erro (48=L, 49=M, 50=Q, 51=H)
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0)
                .append((char) 49).append((char) 69).append((char) 49); // 49 = M

        // Armazena os dados
        int len = dados.length() + 3;
        qr.append((char) 29).append("(k")
                .append((char) (len % 256)).append((char) (len / 256))
                .append((char) 49).append((char) 80).append((char) 48)
                .append(dados);

        // Imprime o QR Code
        qr.append((char) 29).append("(k").append((char) 3).append((char) 0)
                .append((char) 49).append((char) 81).append((char) 48);

        // Pula linha para espaçamento
        qr.append("\n\n");

        return qr.toString();
    }
    
}
