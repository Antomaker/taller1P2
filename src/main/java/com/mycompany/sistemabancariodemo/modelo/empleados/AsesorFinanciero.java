package com.mycompany.sistemabancariodemo.modelo.empleados;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.personas.ClienteNatural;
import java.time.LocalDate;

public class AsesorFinanciero extends Empleado {

    private double comisionBase;
    private double metasMensuales;
    private ClienteNatural[] clientesAsignados;

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
    public String obtenerTipo() {
        return "Asesor Financiero";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return getId();
    }
}