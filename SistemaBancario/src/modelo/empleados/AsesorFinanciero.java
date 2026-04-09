
package modelo.empleados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


import modelo.abstractas.Cliente;
import modelo.abstractas.Empleado;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;

public class AsesorFinanciero extends Empleado implements Consultable, Auditable{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private double comisionBase;
    private double metasMensuales;
    private Cliente[] clientesAsignados;
    private double ventasMensuales;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public AsesorFinanciero(String id, String nombre, String apellido, 
                            LocalDate fechaNacimiento, String email, String legajo, 
                            LocalDate fechaContratacion, double salarioBase, double comisionBase,
                            double metasMensuales) {
        
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase);
        setComisionBase(comisionBase);
        setMetasMensuales(metasMensuales);
        this.clientesAsignados = new Cliente[20];
        this.fechaCreacion = LocalDateTime.now();
        setUltimaModificacion();
        this.usuarioModificacion = "Sistema"; // Por defecto el sistema lo crea
        this.ventasMensuales = 0; // Por defecto en cero
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public double getComisionBase(){ return comisionBase; }
    public double getMetasMensuales(){ return metasMensuales; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    public Cliente[] getClientesAsignados(){
        Cliente[] copia = new Cliente[20];
        System.arraycopy(clientesAsignados, 0, copia, 0, 20);
        return copia;
    }
    public double getVentasMensuales() { return ventasMensuales; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setComisionBase(double comisionBase){
        if (comisionBase <= 0) {
            throw new DatoInvalidoException("Comision Base", comisionBase);
        }
        this.comisionBase = comisionBase;
    }
    
    public void setMetasMensuales(double metasMensuales){
        if (metasMensuales <= 0) {
            throw new DatoInvalidoException("Metas Mensuales", metasMensuales);
        }
        this.metasMensuales = metasMensuales;
    }
    
    public void setUsuarioModificacion(String usuarioModificacion) {
        if (usuarioModificacion == null || usuarioModificacion.isBlank()) {
            throw new DatoInvalidoException("Usuario Modificacion", "Vacio");
        }
        this.usuarioModificacion = usuarioModificacion;
        this.ultimaModificacion  = LocalDateTime.now();
    }
    
    public void setUltimaModificacion(){
        this.ultimaModificacion = LocalDateTime.now();
    }
    
    public void setVentasMensuales(double ventasMensuales) {
        if (ventasMensuales < 0) {
            throw new DatoInvalidoException("Ventas Mensuales", ventasMensuales);
        }
        this.ventasMensuales = ventasMensuales;
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public int calcularEdad() {
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }
    
    @Override
    public String obtenerTipo(){ return "Asesor Financiero"; }
    
    @Override
    public String obtenerDocumentoIdentidad(){ return getId(); }
    
    @Override
    public double calcularSalario(){ return getSalarioBase() + calcularBono(); }

    @Override
    public double calcularBono(){
        if (ventasMensuales >= metasMensuales) {
            return comisionBase;
        }
        return 0;
    }
    
    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    @Override
    public String obtenerResumen() {
        return "Empleado : " + getNombreCompleto() + "\n"
             + "Tipo : " + obtenerTipo() + "\n"
             + "Email : " + getEmail() + "\n"
             + "FechaContratacion : " + getFechaContratacion() + "\n"
             + "Legajo : " + getLegajo() + "\n"
             + "Salario Base : " + getSalarioBase() + "\n"
             + "Comision Base : " + getComisionBase() + "\n"
             + "Metas Mensuales : " + getMetasMensuales();
    }

    @Override
    public boolean estaActivo() {
        return isActivo();
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
