
package modelo.banco;

import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.DatoInvalidoException;

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
            throw new DatoInvalidoException("Nombre", "Vacio");
        }
        this.nombre = nombre;
    }
    
    // ── MÉTODOS ───────────────────────────────────────────────────────
    public void registrarCliente(Cliente c){
        // FALTA CÓDIGO
    }
    
    public void registrarEmpleado(Empleado e){
        // FALTA CÓDIGO
    }
    
    public void abrirCuenta(String idCliente, Cuenta c){
        
    }
    
    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException{
        if (id == null || id.isBlank()) {
            throw new DatoInvalidoException("ID", "Vacio");
        }
        
        Cliente clienteLocalizado = null;
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                clienteLocalizado = c;
                break;
            }
        }
        
        if (clienteLocalizado == null) {
            throw new ClienteNoEncontradoException(id);
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
        // FALTA CÓDIGO
    }
}
