package personas;

package com.mycompany.sistemabancariodemo.modelo.personas;
mport com.mycompany.sistemabancariodemo.modelo.abstractas.Cliente;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Consultable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Notificable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Auditable;
import com.mycompany.sistemabancariodemo.modelo.excepciones.DatoInvalidoException;
import com.mycompany.sistemabancariodemo.modelo.excepciones.CapacidadExcedidaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class ClienteEmpresarial extends Cliente implements consultable, Notificable, Auditable {
    private String nit;
    private String razonSocial;
    private String representanteLegal;
    private Cuenta[] cuentas;
    private int contadorCuentas;
    private boolean activo;
    private boolean aceptaNotificaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public ClienteEmpresarial(String id, String nombre, String apellido, LocalDate fechaNacimiento,
            String email, String nit, String razonSocial, String representanteLegal) {
        
    super(id, nombre, apellido, fechaNacimiento, email);

setNit(nit);
setRazonSocial(razonSocial);
setRepresentanteLegal(representanteLegal);

this.cuentas = new Cuenta[5];
this.contadorCuentas = 0;
this.activo = true;
this.aceptaNotificaciones = true;
this.fechaCreacion = LocalDateTime.now();
this.ultimaModificacion = LocalDateTime.now();
this.usuarioModificacion = "SYSTEM";
    
    }
    
    //LAS VALIDACIONES
     
    public void setNit(String nit) {
        if (nit == null || nit.isEmpty()) {
            throw new DateInvalidoExecption("nit", nit);
            
        }
        this.nit = nit;
    }
    
    public void setRepresentanteLegal(String representanteLegal) {
        if (representanteLegal == null || representanteLegal.isEmpty()) {
            
        }
        this.representanteLegal = representanteLegal;
    }
    
    //LAS CUENTAS
    
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException
            if (contadorCuentas >= cuentas.length) {
                throw CapacidadExcedidaException("Maximo de cuentas alcanzado", cuentas.length);               
            }
    cuentas[contadorCuentas++] = cuenta;
    }
    
  public Cuenta[] getCuentas() {
  Cuenta[] copia = new Cuenta[contadorCuentas];
  System.arraycopy(cuentas, 0, copia, 0, contadorCuentas);
  return copia;
  
  }
    
  //METODO DE ABSTRACTOS
  
  @Override
  public int calcularEdad() {
      return LocalDate.now().getYer() - getFechaNacimiento().getYear();
  }
    
  @Override
  public String obtenerTipo() {
      return "CLIENTES_EMPRESARIAL";
  }
  
  public String obtenerDocumentoIdentidad() {
  return "NIT: " + nit;
  
  }
  
  //LA DE CONSULTABLE
  
  @Override
  public String obtenerResumen() {
      return razonSocial + " - NIT: " + nit;
  }
  
  @Override
  public boolean estaActivo() {
  return activo;
  }
          
  //NOTIFICABLE
  
  @Override
  public boolean notificar(String mensaje) {
  if (aceptaNotificaciones) {
      System.out.println("Notificacion a empresa " + razonSocial + ": " + mensaje);
  }
  }
   
  @Override
  
  public String obtenerContacto(){
  return getEmail();
     }
  }
  
  @Override
    public boolean aceptaNotificaciones() {
        return aceptaNotificaciones;
    }

    //  AUDITABLE 

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return ultimaModificacion;
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return usuarioModificacion;
    }
  
@Override
public void registrarModificacion(String usuario) {
this.ultimaModificacion = LocalDateTime.now();
this.usuarioModificacion = usuario;
 }
    
}
