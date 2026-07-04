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
public class InfoException extends Exception{
    
    public InfoException(String msg){
        super(msg);
    }
    
    public InfoException(String msg, Throwable cause){
        super(msg, cause);
    }
    
}
