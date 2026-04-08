/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
public class DatoInvalidoException extends BancoRuntimeException{
    
    private String campo;
    
    private Object valorRecibido;
    
    public DatoInvalidoException(String mensaje,String campo,Object valorRecibido){
        
        super(mensaje,"ERROR-DATO-INVALIDO");
        
        this.campo=campo;
        
        this.valorRecibido=valorRecibido;
    }
    
    public String getCampo(){
        return campo;
    }
    public Object getValorRecibido(){
        return valorRecibido;
    }
}
