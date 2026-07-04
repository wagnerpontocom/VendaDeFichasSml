/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.print.servico;

/**
 *
 * @author Wagner
 */
import JNumberField.JNumberField;
import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.FLoad;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import br.com.qualix.print.modelo.CapsulePrint;
import br.com.qualix.print.visao.FSimulaPrint;
import br.com.qualix.proserv.modelo.Pedido;
import br.com.qualix.proserv.modelo.PedidoPagamento;
import br.com.qualix.proserv.modelo.PedidoProduto;
import br.com.qualix.proserv.modelo.Produto;
import br.com.qualix.proserv.servico.SPedido;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

//Logo
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.SimpleDoc;

public class PP2 {

    private static PrintService impressora;

    public static void paraImpressora(String texto) {
        imprime(texto);
        acionarGuilhotina();

    }

    public static List<String> retornaImressoras() {
        try {
            List<String> listaImpressoras = new ArrayList<>();
            PrintService[] ps = retornaServicosImpressao();
            for (PrintService p : ps) {
                listaImpressoras.add(p.getName());
            }
            return listaImpressoras;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void detectaImpressoras(String impressoraSelecionada) {
        try {
            impressora = null;
            String nomeSelecionado = impressoraSelecionada;
            if (nomeSelecionado == null || nomeSelecionado.trim().equals("")) {
                nomeSelecionado = Configuracoes.IMPRESSORA;
            }
            if (nomeSelecionado == null || nomeSelecionado.trim().equals("") || nomeSelecionado.equals("Nenhuma Impressora")) {
                return;
            }

            String nomeNormalizado = nomeSelecionado.trim().toLowerCase(Locale.ROOT);
            PrintService[] ps = retornaServicosImpressao();
            for (PrintService p : ps) {
                if (p.getName() != null && p.getName().trim().equalsIgnoreCase(nomeSelecionado.trim())) {
                    impressora = p;
                    return;
                }
            }
            for (PrintService p : ps) {
                if (p.getName() != null && p.getName().toLowerCase(Locale.ROOT).contains(nomeNormalizado)) {
                    impressora = p;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PrintService[] retornaServicosImpressao() {
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);
        if (ps == null || ps.length == 0) {
            ps = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        }
        return ps;
    }

    private static void detectaImpressoraConfigurada() {
        detectaImpressoras(Configuracoes.IMPRESSORA);
    }

    public static boolean imprime(String texto) {

        if (impressora == null) {
            //JOptionPane.showMessageDialog(null, "Nennhuma impressora foi encontrada. Instale uma impressora padr\r\n(Generic Text Only) e reinicie o programa.");
        } else {
            try {
                DocPrintJob dpj = impressora.createPrintJob();
                //InputStream stream = new ByteArrayInputStream((texto + "\n").getBytes("CP437"));
                InputStream stream = new ByteArrayInputStream((texto + "\n").getBytes("CP850"));
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(stream, flavor, null);
                dpj.print(doc, null);
                stream.close();
                return true;
            } catch (PrintException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static void acionarGuilhotina() {
        imprime("\14"); //Nova página (necessário somente para impressora multifunional de papel A4
        imprime("\033m"); //Guilhotina
    }

//  public static void main(String[] args) {
//    PP2 p = new PP2();
//    p.detectaImpressoras("MP-4200 TH");
//    p.imprime("111111111122222222223333333333444444444455");
//    p.acionarGuilhotina();
//  }
    public static void imprimePedido(CapsulePrint capsule, Long idPedido) throws Exception {
        try {
            detectaImpressoras(Configuracoes.IMPRESSORA);
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        String texto = "";

        //texto += "\u001B" + "!" + "\u0001"; //Impressora 58mm
        //texto += "\033-1"; //underline
        JNumberField jnf = new JNumberField();
        JNumberField jqt = new JNumberField(0);

        //Busca Pedido
        Pedido pedido = new Pedido();
        pedido = SPedido.retrievePedido(idPedido);

        texto += retornaCabecalho();
        texto += "\n";

        if (pedido.getCdPessoa() != null && pedido.getCdPessoa() != 0) {
            texto += retornaCliente(pedido.getCdPessoa());
        } else {
            if (Configuracoes.IMPRESSORA_TAM.equals("58mm")) {
                texto += "Cliente: " + "CLIENTE NAO IDENTIFIC.\n";
            } else {
                texto += "Cliente: " + "CLIENTE NAO IDENTIFICADO\n";
            }
        }

        texto += "\n";

        String dsPedido = "";
        dsPedido += pedido.getTpPedido() + " " + pedido.getId();
        texto += FormataInfoImpressora.formataTextoCentraliza(dsPedido) + "\n\n";

        String aaa = "";
        int saldo = 0;

        if (!pedido.getListaProduto().isEmpty()) {
            BigDecimal soma = BigDecimal.ZERO;
            BigDecimal qtde = BigDecimal.ZERO;

            texto += "#PRODUTOS E SERVICOS\n";
            if (Configuracoes.IMPRESSORA_TAM.equals("58mm")) {
                texto += "Qtd Produto          Total Item\n";
            } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")) {
                texto += "Qtd Produto                           Total Item\n";
            } else {
                texto += "Qtd Produto                    Total Item\n";
            }
            for (PedidoProduto prod : pedido.getListaProduto()) {
                if (prod.getQtItem() != null && prod.getQtItem().doubleValue() != 0) {
                    BigDecimal valorTotalItem = prod.getVlItem().multiply(prod.getQtItem());
                    texto += FormataInfoImpressora.formataProduto(prod) + "\n";
                    soma = soma.add(valorTotalItem);
                    qtde = qtde.add(prod.getQtItem());
                }
            }
            jnf.setValue(soma);

            aaa += "TOTAL: (" + qtde + ") " + jnf.getText();
            if (Configuracoes.IMPRESSORA_TAM.equals("58mm")) {
                saldo = 31 - aaa.length();
            } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")) {
                saldo = 48 - aaa.length();
            } else {
                saldo = 41 - aaa.length();
            }
            for (int i = 1; i <= saldo; i++) {
                aaa = " " + aaa;
            }

            String barra = "";
            for (int i = 1; i <= aaa.length(); i++) {
                barra += "-";
            }
            barra += "\n";
            texto += aaa + "\n" + barra;
        }

        if (!pedido.getListaPagamento().isEmpty()) {
            aaa = "";
            saldo = 0;
            BigDecimal soma = BigDecimal.ZERO;
            texto += "#PAGAMENTOS\n";
            for (PedidoPagamento pgto : pedido.getListaPagamento()) {
                soma = soma.add(pgto.getVlPagamento());
                texto += FormataInfoImpressora.formataPagamento(pgto) + "\n";
            }
            aaa += "TOTAL PAGAMENTOS: " + jnf.getText();
            if (Configuracoes.IMPRESSORA_TAM.equals("58mm")) {
                saldo = 31 - aaa.length();
            } else if (Configuracoes.IMPRESSORA_TAM.equals("90mm")) {
                saldo = 48 - aaa.length();
            } else {
                saldo = 41 - aaa.length();
            }
            for (int i = 1; i <= saldo; i++) {
                aaa = " " + aaa;
            }
            String barra = "";
            for (int i = 1; i <= aaa.length(); i++) {
                barra += "-";
            }
            barra += "\n";
            texto += aaa + "\n" + barra;

        }

        if (pedido.getDtValidade() != null) {
            try {
                texto += "Validade do Orcamento: " + Pandora.LD_STR(pedido.getDtValidade()) + "\n\n";
            } catch (Exception ex) {

            }
        }

        if (pedido.getDsEntrega() != null && !pedido.getDsEntrega().equals("")) {
            try {
                texto += "Informacoes de Entrega:\n" + FormataInfoImpressora.quebraEm42(pedido.getDsEntrega()) + "\n\n";
            } catch (Exception ex) {

            }
        }

        String dsObs = "";
        if (pedido.getDsObs() != null && !pedido.getDsObs().equals("")) {
            texto += "Observacao:\n";
            pedido.setDsObs(FormataInfoImpressora.quebraEm42(pedido.getDsObs()));
            texto += FormataInfoImpressora.retiraAcentuacao(pedido.getDsObs()) + "\n\n"; //Talvez seja necessário quebrar linhas de 42 em 42.
        }

        //texto += "\033m ola";
        //texto += "\u001B" + "!" + "\u0018"; // ESC ! n    //deixou negritao grande
        //texto += "\u001B" + "E"; NEGRITO
        //texto += "\u001B" + "!" + "\u0001"; //fonte pequeninha
        //texto += "\033!1 AGRADECEMOS A PREFERENCIA\n"; //Maior
        //texto += "\033M1AGRADECEMOS A PREFERENCIA\n"; //Legal pequeninho
        texto += "\n";
        texto += FormataInfoImpressora.formataTextoCentraliza("AGRADECEMOS A PREFERENCIA") + "\n";
        try {
            //texto += "\n";
            texto += FormataInfoImpressora.formataTextoCentraliza(retornaDataHora(pedido.getDtTransacao()));
        } catch (Exception e) {
        }

        texto += "\n\n\n\n\n";

        if (capsule == null) {
            imprime(texto);
            acionarGuilhotina();
        } else {

            Map params = new HashMap();
            params.put("texto", texto);
            params.put("edit", "NOEDIT");
            FSimulaPrint paper = new FSimulaPrint(capsule, params);

            paper.setModal(true);
            paper.setVisible(true);
        }

    }

    public static void imprimeRelatorioPedido(CapsulePrint capsule) throws Exception {
//        try {
//            detectaImpressoras(Configuracoes.IMPRESSORA);
//        } catch (Exception e){
//            Pandora.msgException(e);
//        }
//
//        
//        String texto = "";
//        
//        
//        texto += retornaCabecalho();
//        texto += "\n";
//        
//            
//        texto += "\n";
//        
//        
//        
//        
//
//         
//         
//            //texto += "\033!1 AGRADECEMOS A PREFERENCIA\n"; //Maior
//            //texto += "\033M1AGRADECEMOS A PREFERENCIA\n"; //Legal pequeninho
//        
//        texto += "\n";
//        texto += FormataInfoImpressora.formataTextoCentraliza("AGRADECEMOS A PREFERENCIA") + "\n";
//        try {
//            //texto += "\n";
//            texto += FormataInfoImpressora.formataTextoCentraliza(retornaDataHora(LocalDateTime.now()));
//        } catch (Exception e) {
//        }
//            
//        texto += "\n\n\n\n\n";
//        
//        if (capsule == null){
//            imprime(texto);
//            acionarGuilhotina();
//        } else {
//
//            
//            Map params = new HashMap();
//            params.put("texto", texto);
//            params.put("edit", "NOEDIT");
//            FSimulaPrint paper = new FSimulaPrint(capsule, params);
//            
//            paper.setModal(true);
//            paper.setVisible(true);
//        }

        FLoad faguarde = new FLoad();
        Thread t = new Thread() {
            public void run() {
                faguarde.setVisible(true);
                try {
                    Relatorio.print(capsule.getLista(), capsule.getParams(), capsule.getDsCaminho());
                } catch (Exception ex) {
                    faguarde.dispose();
                    Pandora.msgInfo("Erro ao abrir Relatório\n" + ex.getMessage());
                }
                faguarde.dispose();
            }
        };
        t.start();

    }

    public static String retornaDataHora(LocalDateTime dataHora) throws Exception {
        String datahora = "";
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
        datahora = Pandora.LDT_STR(dataHora);
        return datahora;
    }

    public static String retornaData(LocalDate data) throws Exception {
        String datahora = "";
        if (data == null) {
            data = LocalDate.now();
        }
        datahora = Pandora.LD_STR(data);
        return datahora;
    }

    public static String retornaHora(LocalDateTime dataHora) throws Exception {
        String datahora = "";
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
        datahora = Pandora.LDT_STR(dataHora).substring(11);
        return datahora;
    }

    private static String retornaCabecalho() {
        String texto = "";

        Pessoa empresa = new Pessoa();
        try {
            empresa = SPessoa.retrievePessoa(1L);
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        texto += FormataInfoImpressora.formataTextoCentraliza(FormataInfoImpressora.quebraEm42(empresa.getNmPessoa())) + "\n";
        if (empresa.getNrCPFCNPJ() != null && !empresa.getNrCPFCNPJ().equals("")) {
            String dsTipo = "";
            if (empresa.getTpPessoa().equals("Pessoa Física")) {
                dsTipo = "CPF";
            } else {
                dsTipo = "CNPJ";
            }

            texto += dsTipo + ": " + empresa.getNrCPFCNPJ() + "\n";
        }

        String dsTelefone = "";
        if (empresa.getNrTelefone1() == null || empresa.getNrTelefone1().equals("")) {
            dsTelefone = "";
        } else {
            dsTelefone = empresa.getNrTelefone1();
            texto += "Telefone: " + dsTelefone + "\n";
        }

        String dsEndereco = "";
        if (empresa.getDsEndereco() == null || empresa.getDsEndereco().equals("")) {
            dsEndereco = "";
        } else {
            dsEndereco = empresa.getDsEndereco();
            texto += FormataInfoImpressora.quebraEm42("Endereco: " + dsEndereco) + "\n";
        }

        String dsEmail = "";
        if (empresa.getDsEmail() == null || empresa.getDsEmail().equals("")) {
            dsEmail = "";
        } else {
            dsEmail = empresa.getDsEmail();
            texto += FormataInfoImpressora.quebraEm42("E-mail: " + dsEmail) + "\n";
        }

        return texto;
    }

    private static String retornaCliente(Long idCliente) {
        String texto = "";

        Pessoa cliente = new Pessoa();
        try {
            cliente = SPessoa.retrievePessoa(idCliente);
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        String dsCliente = "Cliente: " + cliente.getNmPessoa();
        texto += FormataInfoImpressora.quebraEm42(dsCliente) + "\n";
        if (cliente.getNrCPFCNPJ() != null && !cliente.getNrCPFCNPJ().equals("")) {
            String dsTipo = "";
            if (cliente.getTpPessoa().equals("Pessoa Física")) {
                dsTipo = "CPF";
            } else {
                dsTipo = "CNPJ";
            }

            texto += dsTipo + ": " + cliente.getNrCPFCNPJ() + "\n";
        }

        String dsTelefone = "";
        if (cliente.getNrTelefone1() == null || cliente.getNrTelefone1().equals("")) {
            dsTelefone = "";
        } else {
            dsTelefone = cliente.getNrTelefone1();
            texto += "Telefone: " + dsTelefone + "\n";
        }

        String dsEndereco = "";
        if (cliente.getDsEndereco() == null || cliente.getDsEndereco().equals("")) {
            dsEndereco = "";
        } else {
            dsEndereco = "Endereco: " + cliente.getDsEndereco();
            texto += FormataInfoImpressora.quebraEm42(dsEndereco) + "\n";

        }

        String dsEmail = "";
        if (cliente.getDsEmail() == null || cliente.getDsEmail().equals("")) {
            dsEmail = "";
        } else {
            dsEmail = cliente.getDsEmail();
            texto += "E-mail: " + dsEmail + "\n";
        }

        return texto;
    }

    public static void imprimeTextoSimples(String textoPar) {

        char ESC = 0x1B;
        String centralizar = ESC + "a" + (char) 0;

        String texto = centralizar;

        texto += retornaCabecalho();
        texto += "\n";
        texto += textoPar;

        texto = FormataInfoImpressora.retiraAcentuacao(texto);

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        imprime(texto);
        acionarGuilhotina();

    }

    public static void imprimeJunino(String codigo, String textoPar) {

        char ESC = 0x1B;
        String centralizar = ESC + "a" + (char) 1;

        String texto = centralizar;
        try {
            texto += (char) 27 + "!" + (char) 32;
            texto += "Barraca";
            texto += (char) 27 + "!" + (char) 48 + "";
            texto += "\nPEQUENO PRINCIPE";
            texto += "\n";
            texto += (char) 27 + "!" + (char) 0 + "";
//            texto += FormataInfoImpressora.formataTextoCentraliza("4"+ (char) 167 +" Arraial Municipal - 2025");
            texto += "4" + (char) 167 + " Arraial Municipal - 2025";
        } catch (Exception e) {
        }

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }
        texto += "\n\n";
        texto += "Ficha de Produtos";
        texto += "\n\n";
        texto += "\033!1 " + textoPar + " \n"; //Maior

        texto += "\n";
        texto += (char) 27 + "!" + (char) 0 + "";
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

    public static void imprimeCaminhada(String codigo, String textoPar, String dsTipo, String cdTerminal) {

        char ESC = 0x1B;
        String centralizar = ESC + "a" + (char) 1;

        String texto = centralizar;

        try {
            // Título principal (largura + altura dupla)
            texto += (char) 27 + "!" + (char) 0;
            texto += "BARRACA";

            // Tamanho normal
            texto += (char) 27 + "!" + (char) 48;
            texto += "\n" + Configuracoes.EMPRESA.getNmPessoa();
            texto += "\n";
//        texto += "10" + (char) 166 + " Caminhada da Natureza";
            texto += (char) 27 + "!" + (char) 0;
            if (Configuracoes.IMPRESSORA.equals("MP-4200 TH")){
                texto += "5" + (char) 167 + " Arraial Municipal - 2026";
            } else {
                texto += "5.o Arraial Municipal - 2026";
            }
                
        } catch (Exception e) {
        }

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        texto += "\n\n";
        texto += "Ficha de Produtos";
        texto += "\n\n";

        // Produtos - largura dupla (maior que o normal, menor que o título)
        texto += (char) 27 + "!" + (char) 16;
        texto += textoPar;
        texto += "\n";

        // Volta ao tamanho normal
        texto += (char) 27 + "!" + (char) 0;

        texto += "\n";
        texto += "Valido somente para a festa de 2026\n";
        texto += "\n";

        if (!codigo.equals("")) {
            texto += codigoBarrasSimples(codigo);
            // texto += codigoQR(codigo);
            // texto += codigoQR2("Wagner de Almeida Silveira");
        }

        if (dsTipo != null) {
            if (cdTerminal != null && !cdTerminal.equals("")) {
                texto += cdTerminal + " ";
            }
            texto += "PG-" + dsTipo;
        }

        texto += "\n\n\n";

        imprime(texto);
        acionarGuilhotina();
    }

    public static void imprimeCaminhada_old(String codigo, String textoPar, String dsTipo, String cdTerminal) {

        char ESC = 0x1B;
        String centralizar = ESC + "a" + (char) 1;

        String texto = centralizar;
        try {
            texto += (char) 27 + "!" + (char) 32;
            texto += "CMEI BRUNETTA";
            texto += (char) 27 + "!" + (char) 48 + "";
            texto += "\nAPMF CMEI BRUNETTA";
            texto += "\n";
            texto += (char) 27 + "!" + (char) 0 + "";
//            texto += FormataInfoImpressora.formataTextoCentraliza("4"+ (char) 167 +" Arraial Municipal - 2025");
            texto += "10" + (char) 166 + " Caminhada da Natureza";
        } catch (Exception e) {
        }

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }
        texto += "\n\n";
        texto += "Ficha de Produtos";
        texto += "\n\n";

//        texto += "\033!1 " + textoPar + " \n"; //Maior
        texto += (char) 27 + "E" + (char) 1;      // Negrito
        texto += (char) 27 + "!" + (char) 16;     // Largura dupla
        texto += textoPar + "\n";
        texto += (char) 27 + "!" + (char) 0;      // Normal
        texto += (char) 27 + "E" + (char) 0;      // Desliga negrito

        texto += "\n";
        texto += (char) 27 + "!" + (char) 0 + "";
        texto += "Valido para a caminhada de 2025\n";

//    texto += (char)27 + "!" + (char)16 + "Texto largura dupla\n";
//    texto += (char)27 + "!" + (char)32 + "Texto altura dupla\n";
        texto += "\n";
        if (!codigo.equals("")) {
            texto += codigoBarrasSimples(codigo);
//            texto += codigoQR(codigo);
//            texto += codigoQR2("Wagner de Almeida Silveira");
        }

        if (dsTipo != null) {
            if (cdTerminal != null && !cdTerminal.equals("")) {
                texto += cdTerminal + " ";
            }
            texto += "PG-" + dsTipo;
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

    public static void imprimeComLogo(String codigo, String textoPar, String dsTipo, String cdTerminal) {

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // LOGO OPCIONAL
            // Para não imprimir logo, passe null ou "" no caminho.
            imprimirLogo(out, Configuracoes.CAMINHO_DADOS + "logo.png", 350, true);

            char ESC = 0x1B;

            out.write(new byte[]{0x1B, 0x61, 0x01}); // centralizar

            out.write((ESC + "!" + (char) 0).getBytes("CP850"));
            out.write("BARRACA\n".getBytes("CP850"));

            out.write((ESC + "!" + (char) 48).getBytes("CP850"));
            out.write((Configuracoes.EMPRESA.getNmPessoa() + "\n").getBytes("CP850"));

            out.write((ESC + "!" + (char) 0).getBytes("CP850"));
            out.write(("5" + (char) 167 + " Arraial Municipal - 2026\n").getBytes("CP850"));

            out.write("\nFicha de Produtos\n\n".getBytes("CP850"));

            // Produtos maior que normal
            out.write((ESC + "!" + (char) 16).getBytes("CP850"));
            out.write((textoPar + "\n").getBytes("CP850"));

            // Volta normal
            out.write((ESC + "!" + (char) 0).getBytes("CP850"));

            out.write("\nValido somente para a festa de 2026\n\n".getBytes("CP850"));

            if (codigo != null && !codigo.equals("")) {
                out.write(codigoBarrasSimples(codigo).getBytes("CP850"));
            }

            if (dsTipo != null) {
                if (cdTerminal != null && !cdTerminal.equals("")) {
                    out.write((cdTerminal + " ").getBytes("CP850"));
                }
                out.write(("PG-" + dsTipo).getBytes("CP850"));
            }

            out.write("\n\n\n".getBytes("CP850"));

            imprime(out.toByteArray());
            acionarGuilhotina();

        } catch (Exception e) {
            Pandora.msgException(e);
        }
    }

    public static boolean imprime(byte[] dados) {

        if (impressora == null) {
            return false;
        }

        try {
            DocPrintJob dpj = impressora.createPrintJob();

            InputStream stream = new ByteArrayInputStream(dados);
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, null);

            dpj.print(doc, null);
            stream.close();

            return true;

        } catch (PrintException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void imprimirLogo(ByteArrayOutputStream out, String caminhoLogo, int larguraDesejada, boolean centralizar) throws Exception {

        if (caminhoLogo == null || caminhoLogo.trim().equals("")) {
            return;
        }

        File arquivo = new File(caminhoLogo);

        if (!arquivo.exists()) {
            return;
        }

        BufferedImage imagemOriginal = ImageIO.read(arquivo);

        if (imagemOriginal == null) {
            return;
        }

        BufferedImage imagemRedimensionada = redimensionarProporcional(imagemOriginal, larguraDesejada);

        if (centralizar) {
            out.write(new byte[]{0x1B, 0x61, 0x01});
        } else {
            out.write(new byte[]{0x1B, 0x61, 0x00});
        }

        imprimirImagemEscPos(out, imagemRedimensionada);

        out.write("\n".getBytes("CP850"));
    }

    public static BufferedImage redimensionarProporcional(BufferedImage imagemOriginal, int novaLargura) {

        int larguraOriginal = imagemOriginal.getWidth();
        int alturaOriginal = imagemOriginal.getHeight();

        int novaAltura = (alturaOriginal * novaLargura) / larguraOriginal;

        BufferedImage imagemRedimensionada = new BufferedImage(
                novaLargura,
                novaAltura,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g = imagemRedimensionada.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(imagemOriginal, 0, 0, novaLargura, novaAltura, null);
        g.dispose();

        return imagemRedimensionada;
    }

    public static void imprimirImagemEscPos(ByteArrayOutputStream out, BufferedImage imagem) throws Exception {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        int bytesPorLinha = (largura + 7) / 8;

        out.write(0x1D);
        out.write(0x76);
        out.write(0x30);
        out.write(0x00);

        out.write(bytesPorLinha % 256);
        out.write(bytesPorLinha / 256);

        out.write(altura % 256);
        out.write(altura / 256);

        for (int y = 0; y < altura; y++) {
            for (int xByte = 0; xByte < bytesPorLinha; xByte++) {

                int b = 0;

                for (int bit = 0; bit < 8; bit++) {
                    int x = xByte * 8 + bit;

                    if (x < largura) {
                        int rgb = imagem.getRGB(x, y);

                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int blue = rgb & 0xff;

                        int media = (r + g + blue) / 3;

                        if (media < 128) {
                            b |= (1 << (7 - bit));
                        }
                    }
                }

                out.write(b);
            }
        }
    }
    
    
    
    public static void imprimeSoImagemPronto(String codigo, String textoPar, String dsTipo, String cdTerminal) {

        try {
            detectaImpressoraConfigurada();
        } catch (Exception e) {
            Pandora.msgException(e);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // LOGO OPCIONAL
            // Para não imprimir logo, passe null ou "" no caminho.
            imprimirLogo(out, Configuracoes.CAMINHO_DADOS + "logo.png", 550, true);

            char ESC = 0x1B;

            out.write(new byte[]{0x1B, 0x61, 0x01}); // centralizar

            out.write((ESC + "!" + (char) 0).getBytes("CP850"));
//            out.write("BARRACA\n".getBytes("CP850"));
//
//            out.write((ESC + "!" + (char) 48).getBytes("CP850"));
//            out.write((Configuracoes.EMPRESA.getNmPessoa() + "\n").getBytes("CP850"));
//
//            out.write((ESC + "!" + (char) 0).getBytes("CP850"));
//            out.write(("5" + (char) 167 + " Arraial Municipal - 2026\n").getBytes("CP850"));
//
//            out.write("\nFicha de Produtos\n\n".getBytes("CP850"));
//
//            // Produtos maior que normal
//            out.write((ESC + "!" + (char) 16).getBytes("CP850"));
//            out.write((textoPar + "\n").getBytes("CP850"));
//
//            // Volta normal
//            out.write((ESC + "!" + (char) 0).getBytes("CP850"));
//
//            out.write("\nValido somente para a festa de 2026\n\n".getBytes("CP850"));
//
//            if (codigo != null && !codigo.equals("")) {
//                out.write(codigoBarrasSimples(codigo).getBytes("CP850"));
//            }
//
//            if (dsTipo != null) {
//                if (cdTerminal != null && !cdTerminal.equals("")) {
//                    out.write((cdTerminal + " ").getBytes("CP850"));
//                }
//                out.write(("PG-" + dsTipo).getBytes("CP850"));
//            }

            out.write("\n\n\n\n\n\n".getBytes("CP850"));

            imprime(out.toByteArray());
            acionarGuilhotina();

        } catch (Exception e) {
            Pandora.msgException(e);
        }
    }
    
    
}
