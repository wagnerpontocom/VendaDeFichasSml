/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.relatorios.servico;

import br.com.qualix.aaaFerramentas.FLoad;
import br.com.qualix.aaaFerramentas.Pandora;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Wagner
 */
public class Reports {
    
    
    public  void print(ArrayList<?> lista, Map params, String dsCaminho) throws JRException{
    
        FLoad faguarde = new FLoad();

        Thread t = new Thread() {
            public void run() {
                faguarde.setVisible(true);
                try {
                    
                    params.put("REPORT_LOCALE", new Locale("pt","BR"));
                    InputStream fonte = Reports.class.getResourceAsStream("/reports/"+ dsCaminho +".jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);
                    JasperPrint print;
                    print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(lista));
                    JasperViewer jv = new JasperViewer(print, false);
                    jv.setTitle("Relatório - Sistemas Qualix");
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
