/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.print.servico;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.FLoad;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.relatorios.servico.Reports;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Window;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Wagner
 */
public class Relatorio {
    
    public static void print(ArrayList<?> lista, String dsCaminho) throws JRException, Exception {
        Map params = new HashMap();
        print(lista, params, dsCaminho);
    }

//    public static void inicia() {
//        try {
//        ArrayList<Object> listaObj = new ArrayList<>();
////        listaObj.addAll(lista);
//            String dsCaminho = "RCidade.jrxml";
//            InputStream fonte = Relatorio.class.getResourceAsStream(dsCaminho);
//            JasperReport report;
//            Map params = new HashMap();
//            params.put("a","a");
//            report = JasperCompileManager.compileReport(fonte); //compila
//            //JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(listaObj)); //monta a partir da fonte de dados.
//            //JasperViewer.viewReport(print, false);
//        } catch (JRException ex) {
//        }
//    }

    public static void print(ArrayList<?> lista, Map params, String dsCaminho) throws JRException, Exception {
//    
        ArrayList<Object> listaObj = new ArrayList<>();
        listaObj.addAll(lista);
        if (dsCaminho.equals("")) {
            throw new Exception("Caminho do relatório é obrigatório");
        }
        //dsCaminho += ".jrxml";
        dsCaminho = "/reports/" + dsCaminho + ".jrxml";
        InputStream fonte = Relatorio.class.getResourceAsStream(dsCaminho);
        JasperReport report = JasperCompileManager.compileReport(fonte); //compila
        
        
        params.put("cor1", Configuracoes.reportCor1);
        params.put("cor2", Configuracoes.reportCor2);
        
        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(listaObj)); //monta a partir da fonte de dados.
        
        JasperViewer jv = new JasperViewer(print, false);
        jv.setTitle("SisteFácil");
        
        
        jv.setIconImage(Configuracoes.LOGO_SISTEMA);
        jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        //jv.setAlwaysOnTop(true);
        jv.setVisible(true);
        
        //JasperViewer.viewReport(print, false);
        //Original
    }
    
    public  void printLoad(ArrayList<?> lista, Map params, String dsCaminho) throws JRException{
    
        FLoad faguarde = new FLoad();

        Thread t = new Thread() {
            public void run() {
                faguarde.setVisible(true);
                try {
                    
                    params.put("REPORT_LOCALE", new Locale("pt","BR"));
                    InputStream fonte = Reports.class.getResourceAsStream("/reports/"+ dsCaminho +".jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);
                    JasperPrint print;
                    
                    params.put("cor1", Configuracoes.reportCor1);
                    params.put("cor2", Configuracoes.reportCor2);
        
                    print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
                    JasperViewer jv = new JasperViewer(print, false);
                    jv.setTitle("SisteFácil");
                    
                    jv.setIconImage(Configuracoes.LOGO_SISTEMA);
                    jv.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                    //jv.setAlwaysOnTop(true);
                    jv.setVisible(true);

                } catch (Exception ex) {
                    faguarde.dispose();
                    Pandora.msgInfo("Erro ao abrir Relatório\n" + ex.getMessage());
                }

                faguarde.dispose();
            }
        };

        t.start();
        
    }

}
