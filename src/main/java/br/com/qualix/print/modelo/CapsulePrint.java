/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.print.modelo;

import java.util.ArrayList;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author Wagner
 */
@Data
public class CapsulePrint {
    private ArrayList<?> lista = new ArrayList<>();
    private Map params;
    private String dsCaminho = "";
}
