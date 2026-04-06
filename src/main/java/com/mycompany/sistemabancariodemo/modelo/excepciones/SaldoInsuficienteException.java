/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
//2.EXCEPTION-SaldoInsuficienteException(cheked)
public class SaldoInsuficienteException extends SistemaBancarioException {
    
    private double saldoActual;
    
    private double montoSolicitado;
    
    public SaldoInsuficienteException(String mensaje, double saldoActual,double montoSolicitado){
        
        super(mensaje, "ERROR-Saldo");
        
        this.saldoActual=saldoActual;
        
        this.montoSolicitado=montoSolicitado;
    }
    
    public double getSaldoActual(){
        return saldoActual;
    }
    public double getMontoSolicitado(){
        return montoSolicitado;
    }
}
