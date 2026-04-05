/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
public class PermisoInsuficienteException extends BancoRuntimeException {
    //tampoco nesecita atributos
    public PermisoInsuficienteException(String accion){
        super(
        "permiso insuficiente: no tiene autorizacion para: "+accion, 
        "ERROR-PERMISO"
        );
    }
    
}
