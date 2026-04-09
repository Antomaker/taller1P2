package modelo.personas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; // Para calcularEdad()

import modelo.abstractas.Cliente;
import modelo.enums.TipoDocumento;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Notificable;

public class ClienteNatural extends Cliente implements Consultable, Notificable, Auditable {
    
    // ── ATRIBUTOS ────────────────────────────────────────────
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public ClienteNatural(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email,
                          TipoDocumento tipoDocumento, String numeroDocumento) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        if (tipoDocumento == null) {
            throw new DatoInvalidoException("TipoDocumento", "Vacio");
        }
        this.tipoDocumento = tipoDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new DatoInvalidoException("NumeroDocumento", "Vacio");
        }
        this.numeroDocumento = numeroDocumento;
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS DE PERSONA ───────────────────────────────────────────────────────
    
    @Override
    public int calcularEdad() {
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }

    @Override
    public String obtenerTipo() {
        return "Cliente Natural";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return tipoDocumento + " : " + numeroDocumento;
    }
    
    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    
    @Override
    public String obtenerResumen() {
        return "ID       : " + getId() + "\n"
             + "Cliente  : " + getNombreCompleto() + "\n"
             + "Tipo     : " + obtenerTipo() + "\n"
             + "FechaNac : " + getFechaNacimiento() + "\n"
             + "Email    : " + getEmail() + "\n"
             + obtenerDocumentoIdentidad();
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
            System.out.println("[Notificacion no entregada -> " + getNombreCompleto() + " no acepta notificaciones]");
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
    
