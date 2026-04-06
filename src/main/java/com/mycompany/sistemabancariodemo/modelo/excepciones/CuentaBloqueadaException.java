/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.excepciones;

/**
 *
 * @author Dell
 */
//3.EXCEPTION-CuentaBloqueadaException(cheked)
public class CuentaBloqueadaException extends SistemaBancarioException {
    // si atributos ya que los hereda del padre
    public CuentaBloqueadaException(String mensaje){
        super(mensaje,"ERROR-BLOQUEADA");
    }      
}
