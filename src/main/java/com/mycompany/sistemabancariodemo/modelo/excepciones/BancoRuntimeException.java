/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;
import java.time.LocalDateTime;
/**
 *
 * @author Dell
 */
public class BancoRuntimeException extends RuntimeException {
    
    private String codigoError;
    
    private LocalDateTime timestamp;
    
    public BancoRuntimeException(String mensaje,String codigoError){
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
