package modelo.abstractas;
 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; // Para calcularEdad()

import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.DatoInvalidoException;
 
public abstract class Cliente extends Persona {
 
    // ── ATRIBUTOS ────────────────────────────────────────────
    private static final int maxCuentas = 5; // Máximo de cuentas
    private Cuenta[] cuentas;
    private int totalCuentas;
    private boolean activo;
    private final LocalDateTime fechaCreacion; // final porque nunca se modifica
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
 
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Cliente(String id, String nombre, String apellido,
                   LocalDate fechaNacimiento, String email) {
        super(id, nombre, apellido, fechaNacimiento, email);
        this.cuentas = new Cuenta[maxCuentas];
        this.totalCuentas = 0; // Por defecto 0
        this.activo = true; // Por defecto el cliente está activo
        this.fechaCreacion = LocalDateTime.now(); 
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = ""; // Ninguna por defecto
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public Cuenta[] getCuentas() {
        Cuenta[] copia = new Cuenta[maxCuentas];
        System.arraycopy(cuentas, 0, copia, 0, maxCuentas);
        return copia;
    }
        
    public int getTotalCuentas() { return totalCuentas; }
    public boolean isActivo() { return activo; }
        // protected porque sólo deben acceder las subclases al método
    protected LocalDateTime getFechaCreacion() { return fechaCreacion; }
    protected LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    protected String getUsuarioModificacion() { return usuarioModificacion; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setActivo(boolean activo) { this.activo = activo; }
    
    protected void setUsuarioModificacion(String usuarioModificacion) {
        if (usuarioModificacion == null || usuarioModificacion.isBlank()) {
            throw new DatoInvalidoException();
        }
        this.usuarioModificacion = usuarioModificacion;
        this.ultimaModificacion  = LocalDateTime.now();
    }
    
    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public void agregarCuenta(Cuenta cuenta) throws CapacidadExcedidaException {
        if (cuenta == null) {
            throw new DatoInvalidoException();
        }
        if (totalCuentas >= maxCuentas) {
            throw new CapacidadExcedidaException();
        }
        cuentas[totalCuentas] = cuenta;
        totalCuentas++;
    }

}
