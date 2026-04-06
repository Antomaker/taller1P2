/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.interfacee;
import java.time.LocalDateTime;
/**
 *
 * @author Dell
 */
//j
public interface Auditable {
    
    public LocalDateTime obetenerFechaDeCreacion();
    
    public LocalDateTime obtenerUltimaModificacion();
    
    public String obtenerUsuarioModificacion();
    
    public void registrarModificacion();  
}
