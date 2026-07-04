/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaPolicyFocus;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.LinkedList;

/**
 *
 * @author Wagner
 */
public class PoliticaFoco extends FocusTraversalPolicy{

    protected final java.util.List<Component> componentes = new LinkedList<>();
    private int focado = 0;
  
    
    public void add(Component componente){
        this.componentes.add(componente);
    }
  
    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
        this.focado = (this.focado + 1) % this.componentes.size();
        return this.componentes.get(focado);
    }

    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {
        this.focado = (this.componentes.size() + this.focado - 1) % this.componentes.size();
        return this.componentes.get(focado);
    }

    @Override
    public Component getFirstComponent(Container aContainer) {
        return this.componentes.get(0);
    }

    @Override
    public Component getLastComponent(Container aContainer) {
        return this.componentes.get(this.componentes.size() - 1);
    }

    @Override
    public Component getDefaultComponent(Container aContainer) {
        return this.componentes.get(0);
    }
    
    public void setDefaultFocus(){
        focado = 0;
    }
    
    public void setFocado(int foco){
        focado = foco;
    }
}
