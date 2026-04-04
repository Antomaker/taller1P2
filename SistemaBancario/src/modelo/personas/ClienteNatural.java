package modelo.personas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit; // Para calcularEdad()

import modelo.abstractas.Cliente;
import modelo.enums.TipoDocumento;
import modelo.excepciones.DatoInvalidoException;

public class ClienteNatural extends Cliente {
    
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
            throw new DatoInvalidoException("TipoDocumento", null);
        }
        this.tipoDocumento = tipoDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            throw new DatoInvalidoException("NumeroDocumento", null);
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
        return tipoDocumento + ": " + numeroDocumento;
    }
    
}
    
