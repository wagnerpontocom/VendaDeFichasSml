/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.proserv.modelo.Pedido;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
public class DPedido {
    
    ArrayList<Pedido> dataBase = new ArrayList<>();
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_PEDIDO + ".txt";
    private Long temp_id_pedido = null;
    
    public Pedido grava(Pedido pedido) throws IOException, Exception{
        dataBase = new ArrayList<>();
        retrieve();
        if (pedido.getId() == null){
            pedido.setId(nextId(dataBase));
            temp_id_pedido = pedido.getId();
            
            if (Configuracoes.VERSAO_GRATUITA && (pedido.getId() < Configuracoes.LIMITE_PEDIDO) && (pedido.getId() == 10L || pedido.getId() == 20L || pedido.getId() == 30L)){
                Thread t = new Thread() {
                    public void run() {
                        try {
                        } catch (Exception ex) {
                        }
                    }
                };
                t.start();
                
                Thread.sleep(2000);
            }
            
            if (Configuracoes.VERSAO_GRATUITA && pedido.getId() > Configuracoes.LIMITE_PEDIDO){
                
                Thread t = new Thread() {
                    public void run() {
                        try {
                        } catch (Exception ex) {
                        }
                    }
                };
                t.start();
                
                throw new Exception(Configuracoes.MSG_PADRAO_LIMITEREGISTRO);
            }
            
            dataBase.add(pedido);
        } else {
            ArrayList<Pedido> listRem = new ArrayList<>();
            for (Pedido g : dataBase) {
                if (g.getId().doubleValue() == pedido.getId().doubleValue()){
                    listRem.add(g);
                }
            }
            dataBase.removeAll(listRem);
            dataBase.add(pedido);
        }
        
        
        try {
            Gson gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(dataBase);
            bw.append(linha);
            bw.close();
            return pedido;
        } catch (Exception e) {
            Gson gson = new Gson();
            File file = new File(caminho_dados);
            file.createNewFile();
            
            
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(dataBase);
            bw.append(linha);
            bw.close();
            return pedido;
        }
    }
    
    
    public void delete(Pedido pedido) throws IOException{
        dataBase = new ArrayList<>();
        retrieve();
        ArrayList<Pedido> listRem = new ArrayList<>();
        for (Pedido g : dataBase) {
            if (g.getId().doubleValue() == pedido.getId().doubleValue()){
                listRem.add(g);
            }
        }
        dataBase.removeAll(listRem);
        
        try {
            Gson gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(dataBase);
            bw.append(linha);
            bw.close();
            
        } catch (Exception e) {
            
        }
        
    }
    
    
    public ArrayList<Pedido> retrieve() throws IOException{
        dataBase = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            dataBase = gson.fromJson(linha, BDPedido.class);
            //carregaParaSession(config);
            br.close();
        } catch (Exception ex) {
            dataBase = new ArrayList<>();
            
            File file = new File(caminho_dados);
            file.createNewFile();
            
            gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(dataBase);
            bw.append(linha);
            bw.close();
            return dataBase;
        }
        return dataBase;
    }
    
    public Long nextId(ArrayList<Pedido> lista){
        Long maior = 0l;
        for (Pedido pedido : lista) {
            if (pedido.getId() != null && pedido.getId() > maior){
                maior = pedido.getId();
            }
        }
        return maior + 1;
    }
    
    
}
