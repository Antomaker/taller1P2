
package modelo.empleados;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit; // Para calcularEdad()

import modelo.abstractas.Empleado;
import modelo.enums.Turno;
import modelo.excepciones.DatoInvalidoException;

public class Cajero extends Empleado{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private Turno turno;
    private String sucursalAsignada;
    private int transaccionesDia;
    
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Cajero(String id, String nombre, String apellido, 
                    LocalDate fechaNacimiento, String email, String legajo,
                    LocalDate fechaContratacion, double salarioBase, Turno turno,
                    String sucursalAsignada, int transaccionesDia) {
        
        super(id, nombre, apellido, fechaNacimiento, email, legajo, 
              fechaContratacion, salarioBase);
        setTurno(turno);
        setSucursalAsignada(sucursalAsignada);
        this.transaccionesDia = 0; // Por defecto 0
        
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public Turno getTurno(){ return turno; }
    public String getSucursalAsignada(){ return sucursalAsignada; }
    public int getTransaccionesDia(){ return transaccionesDia; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    
    public void setSucursalAsignada(String sucursalAsignada){
        if (sucursalAsignada == null || sucursalAsignada.isEmpty()) {
            throw new DatoInvalidoException("Sucursal Asignada", "Vacio");
        }
        this.sucursalAsignada = sucursalAsignada;
    }
    
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public int calcularEdad(){
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }

    @Override
    public String obtenerTipo(){ return "Cajero"; }
    
    @Override
    public String obtenerDocumentoIdentidad(){ return getId(); }
    
    @Override
    public double calcularSalario(){ return getSalarioBase() + calcularBono(); }

    @Override
    public double calcularBono(){
        return transaccionesDia * 10000; // --> Bono de $10.000 por cada transacción
    }
    
}
