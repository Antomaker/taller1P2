/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
//5.EXCEPTION-CapacidadExcedidaException(cheked)
public class CapacidadExcedidaException extends SistemaBancarioException{
    
    private int capacidadMaxima;
    
    public CapacidadExcedidaException(String mensaje,int capacidadMaxima){
        
        super(mensaje,"ERROR-CAPACIDAD-MAXIMA-EXCEDIDA");
        
        this.capacidadMaxima=capacidadMaxima;
    }
    
    public int getCapacidadMaxima(){
        return capacidadMaxima;
    }
    
}
