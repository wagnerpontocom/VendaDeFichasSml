/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaConfig.modelo;

/**
 *
 * @author Wagner
 */

@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class ArquivoConfig {
    private Long id;
    private int contImagePropaganda = 0;
    private String versionId = "";
    private boolean inServer = true;
    private String nmImpressora = "";
    private String dsBobina = "";
    private String cor1 = "";
    private String cor2 = "";
}
