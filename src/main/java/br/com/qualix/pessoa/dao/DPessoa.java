/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.pessoa.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.pessoa.modelo.Pessoa;
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
public class DPessoa {
    
    ArrayList<Pessoa> dataBase = new ArrayList<>();
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_PESSOA + ".txt";
    
    public Pessoa grava(Pessoa pessoa) throws IOException, Exception{
        dataBase = new ArrayList<>();
        retrieve();
        if (pessoa.getId() == null){
            pessoa.setId(nextId(dataBase));
            
            if (Configuracoes.VERSAO_GRATUITA && pessoa.getId() > Configuracoes.LIMITE_PESSOA){
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
            
            dataBase.add(pessoa);
        } else {
            ArrayList<Pessoa> listRem = new ArrayList<>();
            for (Pessoa g : dataBase) {
                if (g.getId().doubleValue() == pessoa.getId().doubleValue()){
                    listRem.add(g);
                }
            }
            dataBase.removeAll(listRem);
            dataBase.add(pessoa);
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
        return pessoa;
    }
    
    
    public void delete(Pessoa pessoa) throws IOException{
        dataBase = new ArrayList<>();
        retrieve();
        ArrayList<Pessoa> listRem = new ArrayList<>();
        for (Pessoa g : dataBase) {
            if (g.getId().doubleValue() == pessoa.getId().doubleValue()){
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
    
    
    public ArrayList<Pessoa> retrieve() throws IOException{
        dataBase = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            dataBase = gson.fromJson(linha, BDPessoa.class);
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
        if (dataBase == null){
            dataBase = new ArrayList<>();
        }
        return dataBase;
    }
    
    public Long nextId(ArrayList<Pessoa> lista){
        Long maior = 0l;
        for (Pessoa pessoa : lista) {
            if (pessoa.getId() != null && pessoa.getId() > maior){
                maior = pessoa.getId();
            }
        }
        return maior + 1;
    }

    
    
}
