/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.estoque.dto;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
public class DTO_Estoque {
    private String grupo;
    private String codigo;
    private String produto;
    private String localizacao;
    private String codigoBarras;
    private String quantidade;
    private String quantidadeMin;
    private String valorUnit;
    private String valorTotalEstoque;
}
