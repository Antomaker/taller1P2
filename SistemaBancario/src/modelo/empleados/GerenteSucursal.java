
package modelo.empleados;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import modelo.abstractas.Empleado;

public class GerenteSucursal extends Empleado{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
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
        this.empleadosACargo = new Empleado[20];
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getSucursal(){ return sucursal; }
    public double getPresupuestoAnual(){ return presupuestoAnual; }
    
    public Empleado[] getEmpleadosACargo(){
        Empleado[] copia = new Empleado[30];
        System.arraycopy(empleadosACargo, 0, copia, 0, 30);
        return copia;
    }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setSucursal(String sucursal){
        if (sucursal == null || sucursal.isEmpty()) {
            throw new IllegalArgumentException("[Error] El campo no puede estar vacio");
        }
        this.sucursal = sucursal;
    }
    
    public void setPresupuestoAnual(double presupuestoAnual){
        if (presupuestoAnual < 0) {
            throw new IllegalArgumentException("[Error] El presupuesto anual debe ser > 0");
        }
        this.presupuestoAnual = presupuestoAnual;
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
    
}
