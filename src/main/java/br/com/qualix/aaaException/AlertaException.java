/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.qualix.aaaException;

/**
 *
 * @author Wagner
 */
public class AlertaException extends Exception{
    
    public AlertaException(String msg){
        super(msg);
    }
    
    public AlertaException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
