/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.abstractas;

import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDate;
/**
 *
 * @author Dell
 */
public abstract class Cliente extends Persona {

   
    
   protected Cuenta[] cuentas;
   protected int contadorCuentas;
    
    public Cliente(String id,String nombre,String apellido,LocalDate fechaNacimiento,String email){
        super(id,nombre,apellido,fechaNacimiento,email);
        
        this.cuentas=new Cuenta[5];
        this.contadorCuentas=0;
    }
    
   public void ageregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
       if(contadorCuentas>=5){
           throw new CapacidadExcedidaException("Capacidad excedida",5);
       }
       cuentas[contadorCuentas]=cuenta;
       contadorCuentas++;
       
   }
   
   public Cuenta[] getCuentas(){
       Cuenta[]copia=new Cuenta[5];
       System.arraycopy(cuentas,0,copia,0,contadorCuentas);
       return copia;
   }
    
    
}
