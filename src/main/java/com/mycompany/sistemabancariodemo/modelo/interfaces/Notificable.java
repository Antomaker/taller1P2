/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.interfaces;

/**
 *
 * @author Dell
 */

public interface Notificable {
    
    public void notificar(String mensaje);
    
    public String obtenerContacto();
    
    public boolean aceptaNotificaciones();
}
