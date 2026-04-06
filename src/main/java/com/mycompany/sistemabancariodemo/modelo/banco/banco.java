package com.mycompany.sistemabancariodemo.modelo.banco;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Cuenta;
import com.mycompany.sistemabancariodemo.modelo.abstractas.Empleado;
import com.mycompany.sistemabancariodemo.modelo.personas.ClienteNatural;

import com.mycompany.sistemabancariodemo.modelo.excepciones.CapacidadExcedidaException;
import com.mycompany.sistemabancariodemo.modelo.excepciones.ClienteNoEncontradoException;

public class banco {

    private String nombre;
    private Empleado[] empleados;
    private ClienteNatural[] clientes;
    private Cuenta[] cuentas;

    private int totalEmpleados = 0;
    private int totalClientes = 0;
    private int totalCuentas = 0;

    public banco(String nombre) {
        this.nombre = nombre;
        this.empleados = new Empleado[50];
        this.clientes = new ClienteNatural[200];
        this.cuentas = new Cuenta[500];
    }

   
    public void registrarCliente(ClienteNatural c) throws CapacidadExcedidaException {
        if (totalClientes >= clientes.length) {
            throw new CapacidadExcedidaException("Capacidad de clientes llena");
        }
        clientes[totalClientes] = c;
        totalClientes++;
    }

   
    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (totalEmpleados >= empleados.length) {
            throw new CapacidadExcedidaException("Capacidad de empleados llena");
        }
        empleados[totalEmpleados] = e;
        totalEmpleados++;
    }

   
    public ClienteNatural buscarCliente(String id) throws ClienteNoEncontradoException {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equals(id)) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("Cliente no encontrado");
    }

    
    public void abrirCuenta(String idCliente, Cuenta c)
            throws ClienteNoEncontradoException, CapacidadExcedidaException {

        ClienteNatural cliente = buscarCliente(idCliente);

        if (totalCuentas >= cuentas.length) {
            throw new CapacidadExcedidaException("Capacidad de cuentas llena");
        }

        cuentas[totalCuentas] = c;
        totalCuentas++;
    }

 
    public double calcularNominaTotal() {
        double total = 0;

        for (int i = 0; i < totalEmpleados; i++) {
            total += empleados[i].calcularSalario();
        }

        return total;
    }

  
    public void calcularInteresesMensuales() {
        for (int i = 0; i < totalCuentas; i++) {
            cuentas[i].calcularInteres();
        }
    }
}