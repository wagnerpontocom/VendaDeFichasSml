/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.proserv.modelo;

/**
 *
 * @author Wagner
 */
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
public class Grupo {
    private Long id;
    private String dsGrupo;
    
    public String toString(){
        return dsGrupo;
    }
}
