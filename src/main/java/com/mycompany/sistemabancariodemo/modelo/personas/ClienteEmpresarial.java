/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.personas;
import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.interfaces.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */
public class ClienteEmpresarial extends Cliente implements Notificable,Auditable,Consultable {
    
    private TipoDeDocumento nit;
    private String razonSocial;
    private String representanteLegal;
    private Cuenta[] cuentas = new Cuenta[5];
    private int contadorCuentas = 0;
    private boolean activo;
    private boolean aceptaNotificaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public ClienteEmpresarial(TipoDeDocumento nit, String razonSocial, String representanteLegal, boolean activo, boolean aceptaNotificaciones, LocalDateTime fechaCreacion, LocalDateTime ultimaModificacion, String usuarioModificacion, String id, String nombre, String apellido, LocalDate fechaNacimiento, String email)throws DatoInvalidoException {
        super(id, nombre, apellido, fechaNacimiento, email);
        setNit(nit);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);
        this.activo = true;             
        this.aceptaNotificaciones = true; 
        this.fechaCreacion = LocalDateTime.now(); 
        this.ultimaModificacion = LocalDateTime.now(); 
        this.usuarioModificacion = "SYSTEM"; 
      
    }
    
    public void setNit(TipoDeDocumento nit)throws DatoInvalidoException{
        if(nit==null){
            throw new DatoInvalidoException("nit no puede ser nulo ni vacio","nit",nit);
        }
        this.nit=nit;
    }
    public void setRazonSocial(String razonSocial)throws DatoInvalidoException{
        if(razonSocial==null || razonSocial.isEmpty()){
            throw new DatoInvalidoException("razon social no puede ser nulo ni vacio","razonSocial", razonSocial);
        }
        this.razonSocial=razonSocial;
    }
    public void setRepresentanteLegal(String representanteLegal)throws DatoInvalidoException{
        if(representanteLegal==null || representanteLegal.isEmpty()){
            throw new DatoInvalidoException("representante legal no puede ser nulo ni vacio","representanteLegal", representanteLegal);
        }
        this.representanteLegal=representanteLegal;
    }
    
    // METODOS ABSTRACTOS
@Override
public int calcularEdad() {
    return LocalDate.now().getYear() - getFechaNacimiento().getYear();
}

@Override
public String obtenerTipo() {
    return "CLIENTE_EMPRESARIAL";
}

@Override
public String obtenerDocumentoDeIdentidad() {
    return "NIT:"  + nit;
}

// CONSULTABLE
@Override
public String ObtenerResumen() {
    return razonSocial + " - NIT: " + nit;
}

@Override
public boolean estaActivo() {
    return activo;
}

// NOTIFICABLE
@Override
public void notificar(String mensaje) {
    if (aceptaNotificaciones) {
        System.out.println("Notificacion a empresa " + razonSocial + ": " + mensaje);
    }
}

@Override
public String obtenerContacto() {
    return getEmail();
}

@Override
public boolean aceptaNotificaciones() { 
    return aceptaNotificaciones;
}

// AUDITABLE
@Override
public LocalDateTime obetenerFechaDeCreacion() {
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

// CUENTAS
public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
    if (contadorCuentas >= cuentas.length) {
        throw new CapacidadExcedidaException("Máximo de cuentas alcanzado", cuentas.length);
    }
    cuentas[contadorCuentas++] = cuenta;
}
}