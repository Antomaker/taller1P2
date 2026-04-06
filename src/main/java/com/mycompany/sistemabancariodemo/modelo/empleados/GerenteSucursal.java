package com.mycompany.sistemabancariodemo.modelo.empleados;
import com.mycompany.sistemabancariodemo.modelo.interfacee.Consultable;
import com.mycompany.sistemabancariodemo.modelo.interfacee.Auditable;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GerenteSucursal extends Empleado 
        implements Consultable, Auditable{

    private String sucursal;
    private double presupuestoAnual;
    private Empleado[] empleadosACargo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion; 
    public GerenteSucursal(String id, String nombre, String apellido,
                           LocalDate fechaNacimiento, String email,
                           String legajo, LocalDate fechaContratacion,
                           double salarioBase, boolean activo,
                           String sucursal, double presupuestoAnual) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase, activo);

        this.sucursal = sucursal;
        this.presupuestoAnual = presupuestoAnual;
        this.empleadosACargo = new Empleado[30];
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = "sistema";
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public double calcularBono() {
        int antiguedad = calcularAntiguedad();
        return 500000 + (antiguedad * 10000);
    }

    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return getId();
    }

    @Override
    public String obtenerResumen() {
        return "Gerente: " + getNombre() + " - Salario: " + calcularSalario();
    }

    @Override
     public boolean estaActivo() {
        return isActivo();
    }

    @Override
    public LocalDateTime obtenerFechaDeCreacion() {
        return fechaCreacion;
    }

    @Override
      public LocalDateTime obtenerUltimaModificacion() {
        return ultimaModificacion;
    }
    

    @Override
        public String obtenerUsuarioModificacion() {
        return usuarioModificacion;
    }

    @Override
        public void registrarModificacion(String usuario) {
        this.ultimaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }

     @Override
    public String obtenerTipo() {
    return "Gerente de Sucursal";
}
    
 
}