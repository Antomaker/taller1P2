
package modelo.banco;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.ClienteNoEncontradoException;

public class Banco {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String nombre;
    private Empleado[] empleados;
    private Cliente[] clientes;
    private Cuenta[] cuentas;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Banco(String nombre){
        setNombre(nombre);
        this.empleados = new Empleado[50]; // Max 50
        this.clientes = new Cliente[200]; // Max 200
        this.cuentas = new Cuenta[500]; // Max 500
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNombre(){ return nombre; }
    
    public Empleado[] getEmpleados(){
        Empleado[] copia = new Empleado[50];
        System.arraycopy(empleados, 0, copia, 0, 50);
        return copia;
    }
    
    public Cliente[] getClientes(){ 
        Cliente[] copia = new Cliente[200];
        System.arraycopy(clientes, 0, copia, 0, 200);
        return copia;
    }
    
    public Cuenta[] getCuentas(){ 
        Cuenta[] copia = new Cuenta[500];
        System.arraycopy(cuentas, 0, copia, 0, 500);
        return copia;
    }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setNombre(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException ("[Error] El campo no puede estar vacio");
        }
        this.nombre = nombre;
    }
    
    // ── MÉTODOS ───────────────────────────────────────────────────────
    public void registrarCliente(Cliente c){
        
    }
    
    public void registrarEmpleado(Empleado e){
        for (int i = 0; i < 50; i++) {
            empleados[i].
        }
    }
    
    public void abrirCuenta(String idCliente, Cuenta c){
        
    }
    
    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException{
        Cliente clienteLocalizado = null;
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                clienteLocalizado = c;
            }
        }
        
        if (clienteLocalizado == null) {
            throw new ClienteNoEncontradoException();
        }
        
        return clienteLocalizado;
    }
    
    public double calcularNominaTotal(){
        double nominaTotal = 0;
        for (Empleado empleado : empleados) {
            nominaTotal += empleado.calcularSalario(); 
        }
        return nominaTotal;
    }
    
    public void calcularInteresesMensuales(){
        
    }
}
