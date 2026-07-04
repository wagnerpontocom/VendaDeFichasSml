/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.pessoa.servico;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.database.dao.service.DaoFactory;
//import br.com.qualix.pessoa.daoPostgreSQL.DConta;
import br.com.qualix.pessoa.dao.DPessoa;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.print.servico.Relatorio;
import br.com.qualix.relatorios.servico.Reports;
import java.awt.Image;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Wagner
 */
public class SPessoa {
    
    //private static DPessoa dpessoa = new DPessoa();
//    private static DPessoa dpessoa = new DPessoa();
    
    public static Pessoa gravaPessoa(Long id, String nmPessoa, String nmApelido, String tpPessoa, String nrCPFCNPJ, String nrRGIE, LocalDate dtNasc, String nrTelefone1, String nrTelefone2, String dsEmail, String dsEndereco, String dsCidade, String dsUF) throws IOException, Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNmPessoa(nmPessoa);
        pessoa.setNmApelido(nmApelido);
        pessoa.setTpPessoa(tpPessoa);
        pessoa.setNrCPFCNPJ(nrCPFCNPJ);
        pessoa.setNrRGIE(nrRGIE);
        pessoa.setDtNasc(dtNasc);
        pessoa.setNrTelefone1(nrTelefone1);
        pessoa.setNrTelefone2(nrTelefone2);
        pessoa.setDsEmail(dsEmail);
        pessoa.setDsEndereco(dsEndereco);
        pessoa.setDsCidade(dsCidade);
        pessoa.setDsUF(dsUF);
//        return dpessoa.grava(pessoa);
        return DaoFactory.getDaoPessoa().grava(pessoa);
    }
    
    public static void deletePessoa(Long id) throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
//        dpessoa.delete(pessoa);
        DaoFactory.getDaoPessoa().delete(pessoa);
    }
    
    public static ArrayList<Pessoa> retrievePessoa() throws Exception{
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();
//        return dpessoa.retrieve();
        return DaoFactory.getDaoPessoa().retrieve();
    }
    
    public static Pessoa retrievePessoa(Long id) throws Exception{
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();
//        listaPessoa = dpessoa.retrieve();
        listaPessoa = DaoFactory.getDaoPessoa().retrieve();
        for (Pessoa pessoa : listaPessoa) {
            if (pessoa.getId().doubleValue() == id.doubleValue()){
                return pessoa;
            }
        }
        return null;
    }
    
    
    
    public static void imprimirFichaPessoa(Long id) throws Exception{
        Pessoa pessoa = retrievePessoa(id);
        imprimirFichaPessoa(pessoa);
    }
    
    public static void imprimirFichaPessoa(Pessoa pessoa) throws Exception{
        
        Reports rp = new Reports();
        Map map = new HashMap();
        
        map.put("dsTituloReport", "FICHA DO CLIENTE");
        
        String nmCliente = "";
        nmCliente += pessoa.getNmPessoa();
        map.put("id", pessoa.getId());
        map.put("nmCliente", nmCliente);
        
        if (pessoa.getDtNasc()!= null){
            map.put("dtNasc", Pandora.LD_STR(pessoa.getDtNasc()));
        }
        
        String infoQR = "";
        map.put("nrCPFCNPJ", pessoa.getNrCPFCNPJ());
        map.put("nrRGIE", pessoa.getNrRGIE());
        map.put("dsEndereco", pessoa.getDsEndereco());
        map.put("nrTelefone1", "(44) 9 9876 5432");
        map.put("nrTelefone2", "(44) 9 9876 5432");
        

        ArrayList<String> lista = new ArrayList<>();
        lista.add("");
        rp.print(lista, map, "RFichaPessoa");
        
    }
    
    public static void imprimirRelatorioPorCidade(ArrayList<String> cidades) throws Exception{
        ArrayList<Pessoa> listPessoa = retrievePessoa();
        ArrayList<Pessoa> listPessoaImprimir = new ArrayList<>();
        
        if (cidades != null && cidades.size() > 0){
            for (Pessoa pessoa : listPessoa) {
                if (cidades.contains(pessoa.getDsCidade()) && pessoa.getId() != 1L){
                    listPessoaImprimir.add(pessoa);
                }
            }
        } else {
            //listPessoaImprimir = listPessoa;
            for (Pessoa pessoa : listPessoa) {
                if (pessoa.getId() != 1L){
                    listPessoaImprimir.add(pessoa);
                }
            }
        }
        
//        Reports rp = new Reports();
        Relatorio rp = new Relatorio();
        Map map = new HashMap();
        
        map.put("dsTitulo", "CLIENTES POR CIDADE");
        map.put("qtTotal", listPessoaImprimir.size());
        
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
        
        Image image = null;
        try {
            File sourceimage = new File("C:/Qualix/sistema/small/logo.png");
            image = ImageIO.read(sourceimage);
            map.put("logo", image);
        } catch (Exception e) {
            SPessoa temp = new SPessoa();
            image = new ImageIcon(temp.getClass().getResource("/assets/logo/logoDefault2.png")).getImage();
            map.put("logo", image);
        }
        
        listPessoaImprimir.sort(Comparator.comparing(Pessoa::getNmPessoa));
        listPessoaImprimir.sort(Comparator.comparing(Pessoa::getDsCidade));
        
//        map.put("inCompleto", "T");
        map.put("inPorCidade", "T");

        rp.printLoad(listPessoaImprimir, map, "reportClientesCidade");
    }
    
    
}
