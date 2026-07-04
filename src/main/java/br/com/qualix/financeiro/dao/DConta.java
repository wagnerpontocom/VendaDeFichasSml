/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.financeiro.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.financeiro.modelo.Conta;
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
public class DConta {
    
    ArrayList<Conta> dataBase = new ArrayList<>();
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_CONTA + ".txt";
    
    public Conta grava(Conta conta) throws IOException, Exception{
        dataBase = new ArrayList<>();
        retrieve();
        if (conta.getId() == null){
            conta.setId(nextId(dataBase));
            
            if (Configuracoes.VERSAO_GRATUITA && conta.getId() > Configuracoes.LIMITE_CONTA){
                throw new Exception(Configuracoes.MSG_PADRAO_LIMITEREGISTRO);
            }
            
            dataBase.add(conta);
        } else {
            ArrayList<Conta> listRem = new ArrayList<>();
            for (Conta g : dataBase) {
                if (g.getId().doubleValue() == conta.getId().doubleValue()){
                    listRem.add(g);
                }
            }
            dataBase.removeAll(listRem);
            dataBase.add(conta);
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
        return conta;
    }
    
    
    public void delete(Conta conta) throws IOException{
        dataBase = new ArrayList<>();
        retrieve();
        ArrayList<Conta> listRem = new ArrayList<>();
        for (Conta g : dataBase) {
            if (g.getId().doubleValue() == conta.getId().doubleValue()){
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
    
    
    public ArrayList<Conta> retrieve() throws IOException{
        dataBase = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            dataBase = gson.fromJson(linha, BDConta.class);
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
    
    public Long nextId(ArrayList<Conta> lista){
        Long maior = 0l;
        for (Conta conta : lista) {
            if (conta.getId() != null && conta.getId() > maior){
                maior = conta.getId();
            }
        }
        return maior + 1;
    }
    
    
}
