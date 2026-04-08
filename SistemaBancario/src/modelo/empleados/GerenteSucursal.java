
package modelo.empleados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import modelo.abstractas.Empleado;
import modelo.cuentas.CuentaCredito;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.PermisoInsuficienteException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;

public class GerenteSucursal extends Empleado implements Consultable, Auditable{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private String sucursal;
    private double presupuestoAnual;
    private Empleado[] empleadosACargo;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public GerenteSucursal(String id, String nombre, String apellido, 
                           LocalDate fechaNacimiento, String email, 
                           String legajo, LocalDate fechaContratacion, 
                           double salarioBase, String sucursal, double presupuestoAnual){
        
        super(id, nombre, apellido, fechaNacimiento, email, legajo, 
                fechaContratacion, salarioBase);
        setSucursal(sucursal);
        setPresupuestoAnual(presupuestoAnual);
        this.empleadosACargo = new Empleado[30];
        this.fechaCreacion = LocalDateTime.now();
        setUltimaModificacion();
        this.usuarioModificacion = "Sistema"; // Por defecto el sistema lo crea
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getSucursal(){ return sucursal; }
    public double getPresupuestoAnual(){ return presupuestoAnual; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    
    public Empleado[] getEmpleadosACargo(){
        Empleado[] copia = new Empleado[30];
        System.arraycopy(empleadosACargo, 0, copia, 0, 30);
        return copia;
    }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setSucursal(String sucursal){
        if (sucursal == null || sucursal.isEmpty()) {
            throw new DatoInvalidoException("Sucursal", "Vacio");
        }
        this.sucursal = sucursal;
    }
    
    public void setPresupuestoAnual(double presupuestoAnual){
        if (presupuestoAnual <= 0) {
            throw new DatoInvalidoException("Presupuesto Anual", presupuestoAnual);
        }
        this.presupuestoAnual = presupuestoAnual;
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
    // ── MÉTODOS ───────────────────────────────────────────────────────
    public void aprobarCredito(Empleado solicitante, CuentaCredito cuenta) {
        if (!(solicitante instanceof GerenteSucursal)) {
            throw new PermisoInsuficienteException();
        }
        System.out.println("Credito aprobado por: " + solicitante.getNombreCompleto()
                         + " para la cuenta: " + cuenta.getNumeroCuenta());
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public int calcularEdad(){
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }

    @Override
    public String obtenerTipo(){ return "Gerente Sucursal"; }
    
    @Override
    public String obtenerDocumentoIdentidad(){ return getId(); }
    
    @Override
    public double calcularSalario(){ return getSalarioBase() + calcularBono(); }

    @Override
    public double calcularBono(){
        double bonoFijoGerencia = 150000;
        double bono = calcularAntiguedad() * 50000 + bonoFijoGerencia;
        return bono; // --> Bono de $50.000 por año de antiguedad + bonoFijoGerencia
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
             + "Sucursal : " + getSucursal() + "\n"
             + "Presupuesto Anual : " + getPresupuestoAnual();
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
