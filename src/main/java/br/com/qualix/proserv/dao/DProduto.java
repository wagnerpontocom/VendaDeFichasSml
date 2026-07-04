/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.dao;

import br.com.qualix.aaaConfig.Configuracoes;
import br.com.qualix.proserv.modelo.Produto;
import br.com.qualix.proserv.modelo.Produto;
import com.google.gson.Gson;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wagner
 */
public class DProduto {

    ArrayList<Produto> dataBase = new ArrayList<>();
    private String caminho_dados = Configuracoes.CAMINHO_DADOS + Configuracoes.CAMINHO_PRODUTO + ".txt";

    public void grava(Produto produto) throws IOException, Exception {
        dataBase = new ArrayList<>();
        retrieve();
//        if (produto.getId() == null){
//            produto.setId(nextId(dataBase));
//            if (Configuracoes.VERSAO_GRATUITA && produto.getId() > Configuracoes.LIMITE_PRODUTO){
//                Thread t = new Thread() {
//                    public void run() {
//                        try {
//                            SEnvioPeriodicoEmail.enviaDadosLimiteAtingido("Produto", Configuracoes.LIMITE_PRODUTO);
//                        } catch (Exception ex) {
//                        }
//                    }
//                };
//                t.start();
//                throw new Exception(Configuracoes.MSG_PADRAO_LIMITEREGISTRO);
//            }
//            
//            dataBase.add(produto);
//        } else {
//            ArrayList<Produto> listRem = new ArrayList<>();
//            for (Produto g : dataBase) {
//                if (g.getId().doubleValue() == produto.getId().doubleValue()){
//                    listRem.add(g);
//                }
//            }
//            dataBase.removeAll(listRem);
//            dataBase.add(produto);
//        }
        if (produto.getId() == null) {
            produto.setId(nextId(dataBase));

            if (Configuracoes.VERSAO_GRATUITA && produto.getId() > Configuracoes.LIMITE_PRODUTO) {
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

            dataBase.add(produto);

        } else {
            boolean atualizado = false;

            for (int i = 0; i < dataBase.size(); i++) {
                Produto g = dataBase.get(i);

                if (g.getId().equals(produto.getId())) {
                    dataBase.set(i, produto);
                    atualizado = true;
                    break;
                }
            }

            if (!atualizado) {
                dataBase.add(produto);
            }
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

    public void delete(Produto produto) throws IOException {
        dataBase = new ArrayList<>();
        retrieve();
        ArrayList<Produto> listRem = new ArrayList<>();
        for (Produto g : dataBase) {
            if (g.getId().doubleValue() == produto.getId().doubleValue()) {
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

    public ArrayList<Produto> retrieve() throws IOException {
        dataBase = new ArrayList<>();
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho_dados), "Windows-1252"));
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
            }
            dataBase = gson.fromJson(linha, BDProduto.class);
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

    public Long nextId(ArrayList<Produto> lista) {
        Long maior = 0l;
        for (Produto produto : lista) {
            if (produto.getId() != null && produto.getId() > maior) {
                maior = produto.getId();
            }
        }
        return maior + 1;
    }

}
