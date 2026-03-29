
package modelo.empleados;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import modelo.abstractas.Cliente;
import modelo.abstractas.Empleado;

public class AsesorFinanciero extends Empleado {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double comisionBase;
    private double metasMensuales;
    private Cliente[] clientesAsignados;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public AsesorFinanciero(String id, String nombre, String apellido, 
                            LocalDate fechaNacimiento, String email, String legajo, 
                            LocalDate fechaContratacion, double salarioBase, double comisionBase,
                            double metasMensuales) {
        
        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase);
        setComisionBase(comisionBase);
        setMetasMensuales(metasMensuales);
        this.clientesAsignados = new Cliente[20];
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public double getComisionBase(){ return comisionBase;}
    public double getMetasMensuales(){ return metasMensuales; }
    
    public Cliente[] getClientesAsignados(){
        Cliente[] copia = new Cliente[20];
        System.arraycopy(clientesAsignados, 0, copia, 0, 20);
        return copia;
    }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setComisionBase(double comisionBase){
        if (comisionBase < 0) {
            throw new IllegalArgumentException("[Error] La comision debe ser > 0");
        }
        this.comisionBase = comisionBase;
    }
    
    public void setMetasMensuales(double metasMensuales){
        if (metasMensuales < 0) {
            throw new IllegalArgumentException("[Error] La meta mensual debe ser > 0");
        }
        this.metasMensuales = metasMensuales;
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
        // FALTA CÓDIGO
        return 0.0;
    }
    
}
