package com.mycompany.sistemabancariodemo.modelo.empleados;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import java.time.LocalDate;

public class GerenteSucursal extends Empleado {

    private String sucursal;
    private double presupuestoAnual;
    private Empleado[] empleadosACargo;

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
    public String obtenerTipo() {
        return "Gerente de Sucursal";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return getId();
    }
}