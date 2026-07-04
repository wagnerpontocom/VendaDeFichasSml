/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaConfig.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.aaaConfig.modelo.ArquivoConfig;
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
public class DArquivoConfig {
    
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_CONFIG + ".txt";
    
    public void grava(ArquivoConfig arquivoConfig) throws IOException{
        
        try {
            Gson gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(arquivoConfig);
            bw.append(linha);
            bw.close();
            
        } catch (Exception e) {
            Gson gson = new Gson();
            File file = new File(caminho_dados);
            file.createNewFile();
            
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            linha = gson.toJson(arquivoConfig);
            bw.append(linha);
            bw.close();
        }
    }
    
    public ArquivoConfig retrieve() throws IOException{
        ArquivoConfig arq = new ArquivoConfig();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            arq = gson.fromJson(linha, ArquivoConfig.class);
            br.close();
        } catch (Exception ex) {
            
            File file = new File(caminho_dados);
            file.createNewFile();
            
            gson = new Gson();
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(caminho_dados));
            String linha = "";
            arq.setId(1l);
            arq.setContImagePropaganda(0);
            arq.setVersionId("xx");
            arq.setNmImpressora("");
            arq.setDsBobina("");
            linha = gson.toJson(arq);
            bw.append(linha);
            bw.close();
            return arq;
        }
        return arq;
    }
    
}
