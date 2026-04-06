/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
//4.EXCEPTION-ClienteNoEncontradoException(cheked)
public class ClienteNoEncontradoException extends SistemaBancarioException{
    
    private String idCliente;
    
    public ClienteNoEncontradoException(String mensaje,String idCliente){
        
        super(mensaje, "ERROR-CLIENTE-NO-ENCONTRADO");
        
        this.idCliente=idCliente;
    }
    
    public String getIdCliente(){
        return idCliente;
    }
 
}
