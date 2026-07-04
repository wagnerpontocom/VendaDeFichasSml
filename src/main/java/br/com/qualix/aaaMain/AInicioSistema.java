/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaMain;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaFerramentas.Pandora;
import br.com.qualix.pessoa.modelo.Pessoa;
import br.com.qualix.pessoa.servico.SPessoa;
import br.com.qualix.proserv.servico.SPedido;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Wagner
 */
public class AInicioSistema {
    
    
    public static void main(String[] args) {
        try {
        
//            try {
//                getVersionPOM();
//                Thread.sleep(400);
//                String SKU = Configuracoes.SKU;
//            } catch (Exception e) {
//                Pandora.ex(e);
//            }
            //###########################
            //###########################
            //###########################
            
            String SKU = "QSP5-0-1";
            Configuracoes.SKU = SKU;
            
            
            
            
            
            //###########################
            //###########################
            //###########################
            
//            Pandora.msgAlerta(SKU.substring(0,1)); //q
//            Pandora.msgAlerta(SKU.substring(1,2)); //s
//            Pandora.msgAlerta(SKU.substring(2,3)); //x
//            Pandora.msgAlerta(SKU.substring(3,4)); //3
            

            try {
                byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(Configuracoes.LOGO_SISTEMA_BASE64);
                //BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                Configuracoes.LOGO_SISTEMA = ImageIO.read(new ByteArrayInputStream(imageBytes));
            } catch (Exception e) {
                
            }
            
            try {
                Configuracoes.EMPRESA = SPessoa.retrievePessoa(1L);
            } catch (Exception e){
                
            }
                
            
            
            String[] versao = SKU.split("-");
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                String lookAndFeel = "Windows";
                
                if (versao[1].equals("4")){
                    lookAndFeel = "Nimbus";
                }

                if (lookAndFeel.equals(info.getName())) {
                    if (versao[1].equals("0")){
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } else if (versao[1].equals("1")){
                        UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    } else if (versao[1].equals("2")){
                        UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    } else if (versao[1].equals("3")){
                        UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    } else {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    }
                    break;
                }
            }
            
            
            String sistema = "v" + SKU.substring(3,4);
            switch(sistema){
                case "v5" :
                    FBackground5 box5 = new FBackground5(SKU);
                    box5.setVisible(true);
                    break;
                default :
            }
            
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        
        try {
            
        } catch (Exception e) {
            
        }
        
        
    }
    
    private static void getVersionPOM() throws Exception{
        String versao = "";
        
        SPedido temp = new SPedido();
//        File fXmlFile = new File(temp.getClass().getResource("/assets/pom.xml").toString());
        File f = new File("pom.xml");
        
//        Gson gson = new Gson();
//        BufferedReader br;
        try {
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "Windows-1252"));
//            String linha = "";
//            while (br.ready()) {
//                linha += br.readLine();
//            }
//            br.close();
            
            //Aqui começa o outor
            SAXReader reader = new SAXReader();
            Document document = reader.read(f);
            Element root = document.getRootElement();
            
            String vv = "";
            root.accept(new VisitorSupport() {
                boolean busca = true;
                @Override
                public void visit(Element node) {
                    if (node.getQualifiedName().equals("version") && busca) {
                        busca = false;
                        Configuracoes.SKU = node.getText();
                    }
                }
            });
            
            //Thread.sleep(400);
        } catch (Exception e) {
            Pandora.msgException(e);
        }

    }
    
    
}
