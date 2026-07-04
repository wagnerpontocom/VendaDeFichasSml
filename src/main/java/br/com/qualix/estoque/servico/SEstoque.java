/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.estoque.servico;

import JNumberField.JNumberField;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.estoque.dto.DTO_Estoque;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import br.com.qualix.print.modelo.CapsulePrint;
import br.com.qualix.print.servico.PP2;
import br.com.qualix.relatorios.servico.Reports;
import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
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
public class SEstoque {
    
    public static void relatorioEstoque(String tipoPedido, ArrayList<DTO_Estoque> lista, String dsVlTotal, String qtItens, String qtItensEstq) throws Exception{
        JNumberField jnf = new JNumberField();
        Reports rp = new Reports();
        Map map = new HashMap();
       
        //map.put("tpPedido", tipoPedido);
        map.put("tpPedido", tipoPedido);
        //map.put("vlTotalItens", tipoPedido);
        map.put("vlTotalItens", dsVlTotal);
        map.put("vlQtItens", qtItens);
        map.put("vlQtItensEstq", qtItensEstq);
        
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
        
        CapsulePrint capsule = new CapsulePrint();
        capsule.setDsCaminho("reportEstoque");
        capsule.setLista(lista);
        capsule.setParams(map);
        PP2.imprimeRelatorioPedido(capsule);
        
    }
    
}
