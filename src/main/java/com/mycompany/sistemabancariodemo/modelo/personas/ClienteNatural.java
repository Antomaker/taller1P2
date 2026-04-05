/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.personas;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Auditable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Notificable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Consultable;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cliente;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import com.mycompany.sistemabancariodemo.modelo.abstractas.*;
import com.mycompany.sistemabancariodemo.modelo.enums.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @author DELL
 */
public class ClienteNatural extends Cliente implements Consultable, Notificable, Auditable {
    
    private TipoDeDocumento tipoDocumento;
    private String numeroDocumento;
    private boolean activo;
    private boolean aceptaNotificaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private Cuenta[] cuentas = new Cuenta[5];
    private int contadorCuentas = 0;
    
    public ClienteNatural(String id, String nombre,String apellido, LocalDate fechaNacimiento,String email,TipoDeDocumento tipoDocumento, String numeroDocumento)throws  DatoInvalidoException{
     
        super(id,nombre,apellido,fechaNacimiento,email);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        this.activo = true;
        this.aceptaNotificaciones = true;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SYSTEM";
        
    }
    
    // PARA VALIDACIONES
    
   public void setTipoDocumento(TipoDeDocumento tipoDocumento)throws DatoInvalidoException {
        if (tipoDocumento == null) {
            throw new DatoInvalidoException("tipoDocumento","tipo de documento",tipoDocumento);
        }
        this.tipoDocumento = tipoDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento)throws DatoInvalidoException {
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new DatoInvalidoException("numero de documento","numero de documento",numeroDocumento);
        }
        this.numeroDocumento = numeroDocumento;
    }
    
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
    if (contadorCuentas >= cuentas.length) {
        throw new CapacidadExcedidaException("Maximo de cuentas alcanzado", 5);
    }
    cuentas[contadorCuentas++] = cuenta;
}
    public Cuenta[] getCuentas() {
    Cuenta[] copia = new Cuenta[contadorCuentas];
    System.arraycopy(cuentas, 0, copia, 0, contadorCuentas);
    return copia;
}
    
    
    //LOS METODO ABSTRACTOS
    
    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }
    
    @Override
    public String obtenerTipo() {
        return "CLIENTE_NATURAL";
    }
    //CONSULTABlE
    @Override
    public String ObtenerResumen(){
        return getNombreCompleto()+"-"+tipoDocumento+"-"+numeroDocumento;
    }
    @Override
    public String obtenerDocumentoDeIdentidad() {
        return  tipoDocumento+ ":" +numeroDocumento;
    }
     
   
    @Override
    public boolean estaActivo() {
        return activo;
    }
    
    
    //  NOTIFICABLE
    
    @Override
    public void notificar(String mensaje) {
        if (aceptaNotificaciones) {
            System.out.println("Notificacion a empresa " + getNombreCompleto() + ": " + mensaje);
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
    public LocalDateTime obetenerFechaDeCreacion()  {
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
