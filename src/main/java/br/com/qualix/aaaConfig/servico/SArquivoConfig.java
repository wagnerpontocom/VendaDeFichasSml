/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaConfig.servico;

import br.com.qualix.aaaConfig.dao.DArquivoConfig;
import br.com.qualix.aaaConfig.modelo.ArquivoConfig;
import java.io.IOException;

/**
 *
 * @author Wagner
 */
public class SArquivoConfig {
    
    public static void gravaArquivo(ArquivoConfig arquivoConfig) throws IOException{
        DArquivoConfig dao = new DArquivoConfig();
        dao.grava(arquivoConfig);
    }
    
    public static ArquivoConfig buscaArquivo() throws IOException{
        DArquivoConfig dao = new DArquivoConfig();
        return dao.retrieve();
    }
    
    public static int getPosicaoPublicidade(){
        DArquivoConfig dao = new DArquivoConfig();
        ArquivoConfig arq = new ArquivoConfig();
        try {
            arq = dao.retrieve();
        } catch (Exception e) {
            return 0;
        }
        return arq.getContImagePropaganda();
    }
    
    public static void alteraPosicaoPublicidade(int seq) throws IOException{
        DArquivoConfig dao = new DArquivoConfig();
        ArquivoConfig arq = buscaArquivo();
        arq.setContImagePropaganda(seq);
        dao.grava(arq);
    }
    
}
