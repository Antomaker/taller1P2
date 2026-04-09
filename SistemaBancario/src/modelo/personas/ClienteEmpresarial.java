package modelo.personas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import modelo.abstractas.Cliente;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;

public class ClienteEmpresarial extends Cliente implements Consultable, Notificable, Auditable{
    
    // ── ATRIBUTOS ────────────────────────────────────────────
    private String nit;
    private String razonSocial;
    private String representanteLegal;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public ClienteEmpresarial(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial, String representanteLegal) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setNit(nit);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNit() { return nit; }
    public String getRazonSocial() { return razonSocial; }
    public String getRepresentanteLegal() { return representanteLegal; }
    
    // ── SETTERS ─────────────────────────────────────────────────────── 
    public void setNit(String nit) {
        if (nit == null || nit.isEmpty()) {
            throw new DatoInvalidoException("NIT", "Vacio");
        }
        this.nit = nit;
    }

    public void setRazonSocial(String razonSocial) {
        if (razonSocial == null || razonSocial.isEmpty()) {
            throw new DatoInvalidoException("Razon Social", "Vacio");
        }
        this.razonSocial = razonSocial;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        if (representanteLegal == null || representanteLegal.isEmpty()) {
            throw new DatoInvalidoException("Representante Legal", "Vacio");
        }
        this.representanteLegal = representanteLegal;
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS DE PERSONA ─────────────────────────────────────────────────────── 
    
    @Override
    public int calcularEdad() {
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }

    @Override
    public String obtenerTipo() {
        return "Cliente Empresarial";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return "NIT : " + nit;
    }
    
    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    
    @Override
    public String obtenerResumen() {
        return "ID       : " + getId() + "\n"
             + "Cliente  : " + getNombreCompleto() + "\n"
             + "Tipo     : " + obtenerTipo() + "\n"
             + "FechaNac : " + getFechaNacimiento() + "\n"
             + "Email    : " + getEmail() + "\n"
             + obtenerDocumentoIdentidad() + "\n"
             + "Razon social : " + getRazonSocial() + "\n"
             + "Representante legal : " + getRepresentanteLegal();
    }

    @Override
    public boolean estaActivo() {
        return isActivo();
    }

    @Override
    public void notificar(String mensaje) {
        if (aceptaNotificaciones()) {
        System.out.println("[Notificacion para " + getNombreCompleto() + "]: " + mensaje);
        } else {
            System.out.println("[Notificacion no entregada — " + getNombreCompleto() + " no acepta notificaciones]");
        }
    }

    @Override
    public String obtenerContacto() {
        return getEmail();
    }

    @Override
    public boolean aceptaNotificaciones() {
        return notificacionActiva();
    }

    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return getFechaCreacion();
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return getUltimaModificacion();
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return getUsuarioModificacion();
    }

    @Override
    public void registrarModificacion(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            throw new DatoInvalidoException("Usuario", "Vacio");
        }
        setUsuarioModificacion(usuario);
        setUltimaModificacion();
    }

}