/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
import java.time.LocalDateTime;
//1-Exception-SistemaBancarioException(checked)

public class SistemaBancarioException extends Exception {
    
    private String codigoError;
    
    private LocalDateTime timestamp;
    
    public SistemaBancarioException( String mensaje, String codigoError ){
        
        super(mensaje);
        
        this.codigoError=codigoError;
        
        this.timestamp=LocalDateTime.now();
    }
    
    public String getCodigoError(){
        return codigoError;
    }
    
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    
    
}
