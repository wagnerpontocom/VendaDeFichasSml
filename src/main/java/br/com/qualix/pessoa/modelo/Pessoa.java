/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.pessoa.modelo;

import java.time.LocalDate;

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class Pessoa {
    private Long id;
    private String nmPessoa;
    private String nmApelido;
    private String tpPessoa;
    private String nrCPFCNPJ;
    private String nrRGIE;
    private LocalDate dtNasc;
    private String nrTelefone1;
    private String nrTelefone2;
    private String dsEmail;
    private String dsEndereco;
    private String dsCidade;
    private String dsUF;
    
    public String toString(){
        return nmPessoa;
    }
    
    
    
    //Campos para report
    public String getEnderecoLiteral(){
        String dsEnd = "";
        if (dsEndereco != null && !dsEndereco.equals("")){
            dsEnd += dsEndereco;
        }
        if (dsCidade != null && !dsCidade.equals("") ){
            if (dsEnd.length() != 0){
                dsEnd += " - ";
            }
            dsEnd += dsCidade;
        }
        if (dsUF != null && !dsUF.equals("") ){
            if (dsCidade != null && !dsCidade.equals("")){
                dsEnd += "-";
            }
            dsEnd += dsUF;
        }
//        if (dsEnd.equals("")){
//            return null;
//        }
        return dsEnd;
    }
}
