/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
public class EstadoTransaccionInvalidoException extends BancoRuntimeException {
    //no nesecita atibutos
    public EstadoTransaccionInvalidoException(String estadoOrigen,String estadoDestino){
        super(
         "Transición inválida: no se puede pasar de " 
            + estadoOrigen + " a " + estadoDestino,
            "ERROR-ESTADO-INVALIDO"
        );
    }
    
}
