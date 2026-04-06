/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personas;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Cliente;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.enums.TipoDocumento;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Consultable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Notificable;
import com.mycompany.sistemabancariodemo.modelo.interfaces.Auditable;
import com.mycompany.sistemabancariodemo.modelo.excepciones.DatoInvalidoException;
import com.mycompany.sistemabancariodemo.modelo.excepciones.CapacidadExcedidaException;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @author DELL
 */
public class ClientesNatural extends Cliente implements consultable, Notificable, Auditable {
    
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private Cuenta[] cuenta;
    private int contadorCuentas;
    private boolean activo;
    private boolean aceptaNotificaciones;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    public ClienteNatural(String id, String nombre, Strin apellido, LocalDate fechaNacimiento, String email, TipoDocumento tipoDocumento, String numeroDocumento){
     
        super(id, nombre, apellido, fechaNacimiento, email);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        
        this.cuentas = new Cuentas[5]
        this.contadorCuentas = 0;
        this.activo = true;
        this.aceptaNotificaciones = true;
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "SYSTEM";
        
    }
    
    // PARA VALIDACIONES
    
   public void setTipoDocumento(TipoDocumento tipoDocumento) {
        if (tipoDocumento == null) {
            throw new DatoInvalidoException("tipoDocumento", tipoDocumento);
        }
        this.tipoDocumento = tipoDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new DatoInvalidoException("numeroDocumento", numeroDocumento);
        }
        this.numeroDocumento = numeroDocumento;
    }
    
    // LAS CUENTAS
    
    public void agregarCuenta(Cuenta cuenta) throws CapacidadException {
        if (contadorCuentas >= cuentas.length) {
            throws CapacidadException ("Maximo de cuentas alcanzado", cuentas.length);
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
        return "CLIENTE_EMPRESARIAL";
    }
    
    @Override
    public String obtenerDocumentoIdentidad() {
        return "NIT: " + nit;
    }
     
    // CONSULTABLE
    
    @Override
    public String obtenerResumen() {
        return razonSocial + " - NIT: " + nit;
    }
    
    @Override
    public boolean estaActivo() {
        return activo;
    }
    
    //  NOTIFICABLE
    
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
    public LocaldateTime obtenerFechaCreacion() {
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
