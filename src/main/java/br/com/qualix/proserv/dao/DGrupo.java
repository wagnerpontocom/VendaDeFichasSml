/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.proserv.modelo.Grupo;
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
public class DGrupo {
    
    ArrayList<Grupo> dataBase = new ArrayList<>();
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_GRUPO + ".txt";
    
    public void grava(Grupo grupo) throws IOException{
        dataBase = new ArrayList<>();
        retrieve();
        if (grupo.getId() == null){
            grupo.setId(nextId(dataBase));
            dataBase.add(grupo);
        } else {
            ArrayList<Grupo> listRem = new ArrayList<>();
            for (Grupo g : dataBase) {
                if (g.getId().doubleValue() == grupo.getId().doubleValue()){
                    listRem.add(g);
                }
            }
            dataBase.removeAll(listRem);
            dataBase.add(grupo);
        }
        
        
        try {
            Gson gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(dataBase);
            bw.append(linha);
            bw.close();
            
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
            
        }
        
    }
    
    
    public void delete(Grupo grupo) throws IOException{
        dataBase = new ArrayList<>();
        retrieve();
        ArrayList<Grupo> listRem = new ArrayList<>();
        for (Grupo g : dataBase) {
            if (g.getId().doubleValue() == grupo.getId().doubleValue()){
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
    
    
    public ArrayList<Grupo> retrieve() throws IOException{
        dataBase = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            dataBase = gson.fromJson(linha, BDGrupo.class);
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
    
    public Long nextId(ArrayList<Grupo> lista){
        Long maior = 0l;
        for (Grupo grupo : lista) {
            if (grupo.getId() != null && grupo.getId() > maior){
                maior = grupo.getId();
            }
        }
        return maior + 1;
    }
    
    
}
