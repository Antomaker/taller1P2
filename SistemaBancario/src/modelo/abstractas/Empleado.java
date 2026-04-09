
package modelo.abstractas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit; // Para calcularAntiguedad()
import modelo.excepciones.DatoInvalidoException;


public abstract class Empleado extends Persona{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String legajo;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private boolean activo;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Empleado(String id, String nombre, String apellido, 
                    LocalDate fechaNacimiento, String email, String legajo,
                    LocalDate fechaContratacion, double salarioBase) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setLegajo(legajo);
        setFechaContratacion(fechaContratacion);
        setSalarioBase(salarioBase);
        this.activo = true; // Por defecto activo
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getLegajo(){ return legajo; }
    public LocalDate getFechaContratacion(){ return fechaContratacion; }
    public double getSalarioBase(){ return salarioBase; }
    public boolean isActivo(){ return activo; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setLegajo(String legajo){
        if (legajo == null || legajo.isEmpty()) {
            throw new DatoInvalidoException("Legajo", "Vacio");
        }
        this.legajo = legajo;
    }
    
    public void setFechaContratacion(LocalDate fechaContratacion){
        if (fechaContratacion.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("Fecha de Contratacion", "Fecha futura");
        }
        this.fechaContratacion = fechaContratacion;
    }
    
    public void setSalarioBase(double salarioBase){
        if (salarioBase <= 0) {
            throw new DatoInvalidoException("Salario Base", salarioBase);
        }
        this.salarioBase = salarioBase;
    }
    
    public void setActivo(boolean activo){
        if (activo || !activo) {
            this.activo = activo;
        } else {
            throw new DatoInvalidoException("Activar", activo);
        }
    }
    
    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public int calcularAntiguedad(){
        return (int) ChronoUnit.YEARS.between(getFechaContratacion(), LocalDate.now());
        // ChronoUnit calcula distancias entre fechas
    }
    
    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    public abstract double calcularSalario();
    public abstract double calcularBono();
    
}