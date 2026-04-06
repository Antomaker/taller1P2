/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.interfacee;

/**
 *
 * @author Dell
 */

//2.INTEFACE-TRANSACCIONABLE

public interface Transaccionable {
    
    public void depositar(double monto);
    
    public void retirar(double monto);
    
    public double calculaComision();
    
    public double aceptaNotificaciones();
    
}
