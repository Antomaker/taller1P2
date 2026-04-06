package com.mycompany.sistemabancariodemo.modelo.empleados;
import com.mycompany.sistemabancariodemo.modelo.interfacee.Consultable;
import com.mycompany.sistemabancariodemo.modelo.interfacee.Auditable;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.personas.ClienteNatural;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AsesorFinanciero extends Empleado 
 implements Consultable, Auditable {

    private double comisionBase;
    private double metasMensuales;
    private ClienteNatural[] clientesAsignados;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;

    public AsesorFinanciero(String id, String nombre, String apellido,
                            LocalDate fechaNacimiento, String email,
                            String legajo, LocalDate fechaContratacion,
                            double salarioBase, boolean activo,
                            double comisionBase, double metasMensuales) {

        super(id, nombre, apellido, fechaNacimiento, email,
              legajo, fechaContratacion, salarioBase, activo);

        this.comisionBase = comisionBase;
        this.metasMensuales = metasMensuales;
        this.clientesAsignados = new ClienteNatural[20];
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
        if (metasMensuales > 1000000) {
            return comisionBase;
        }
        return 0;
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
        return "Asesor: " + getNombre() + " - Salario: " + calcularSalario();    
    }
    
    @Override
    public boolean estaActivo() { 
            return isActivo();
         
    }
      
    

    @Override
    public String obtenerTipo() {
       return "Asesor: " + getNombre() + " - Salario: " + calcularSalario();
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
     
}