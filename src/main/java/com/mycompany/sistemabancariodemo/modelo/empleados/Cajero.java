/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.empleados;


 

import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.enums.Turno;
import java.time.LocalDate;

public class Cajero extends Empleado {

    private Turno turno;
    private String sucursalAsignada;
    private int transaccionesDia;

    public Cajero(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email,
                  String legajo, LocalDate fechaContratacion, double salarioBase, boolean activo,
                  Turno turno, String sucursalAsignada) {

        super(id, nombre, apellido, fechaNacimiento, email, legajo, fechaContratacion, salarioBase, activo);

        this.turno = turno;
        this.sucursalAsignada = sucursalAsignada;
        this.transaccionesDia = 0;
    }


    @Override
    public double calcularSalario() {
        return getSalarioBase() + calcularBono();
    }

    @Override
    public double calcularBono() {
        return transaccionesDia * 1000;
    }



    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Cajero";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return getId();
    }


    public Turno getTurno() {
        return turno;
    }

    public String getSucursalAsignada() {
        return sucursalAsignada;
    }

    public int getTransaccionesDia() {
        return transaccionesDia;
    }

   
}