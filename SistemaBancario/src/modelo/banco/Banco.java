
package modelo.banco;

import java.time.LocalDateTime;
import modelo.abstractas.Cliente;
import modelo.abstractas.Cuenta;
import modelo.abstractas.Empleado;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.ClienteNoEncontradoException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;

public class Banco implements Auditable{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String nombre;
    private Empleado[] empleados;
    private int totalEmpleados = 0; // Contador para índices de array
    private Cliente[] clientes;
    private int totalClientes = 0; // Contador para índices de array
    private Cuenta[] cuentas;
    private int totalCuentas = 0; // Contador para índices de array
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Banco(String nombre){
        setNombre(nombre);
        this.empleados = new Empleado[50]; // Max 50
        this.clientes = new Cliente[200]; // Max 200
        this.cuentas = new Cuenta[500]; // Max 500
        this.fechaCreacion = LocalDateTime.now();
        setUltimaModificacion();
        this.usuarioModificacion = "Gerente"; 
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNombre(){ return nombre; }
    
    public Empleado[] getEmpleados(){
        Empleado[] copia = new Empleado[50];
        System.arraycopy(empleados, 0, copia, 0, 50);
        return copia;
    }
    public int getTotalEmpleados() { return totalEmpleados; }
    
    public Cliente[] getClientes(){ 
        Cliente[] copia = new Cliente[200];
        System.arraycopy(clientes, 0, copia, 0, 200);
        return copia;
    }
    public int getTotalClientes() { return totalClientes; }
    
    public Cuenta[] getCuentas(){ 
        Cuenta[] copia = new Cuenta[500];
        System.arraycopy(cuentas, 0, copia, 0, 500);
        return copia;
    }
    public int getTotalCuentas() { return totalCuentas; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setNombre(String nombre){
        if (nombre == null || nombre.isEmpty()) {
            throw new DatoInvalidoException("Nombre", "Vacio");
        }
        this.nombre = nombre;
    }
    
    public void setUsuarioModificacion(String usuarioModificacion) {
        if (usuarioModificacion == null || usuarioModificacion.isBlank()) {
            throw new DatoInvalidoException("Usuario Modificacion", "Vacio");
        }
        this.usuarioModificacion = usuarioModificacion;
        this.ultimaModificacion  = LocalDateTime.now();
    }
    
    public void setUltimaModificacion(){
        this.ultimaModificacion = LocalDateTime.now();
    }
    
    // ── MÉTODOS ───────────────────────────────────────────────────────
    public void registrarCliente(Cliente c) throws CapacidadExcedidaException {
        if (totalClientes >= clientes.length){
            throw new CapacidadExcedidaException(200);
        }
        
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equals(c.getId())) {
                throw new DatoInvalidoException("Registrar Cliente", "ID ya existente");
            }
        }
        clientes[totalClientes] = c;
        totalClientes++;
    }
    
    public void registrarEmpleado(Empleado e) throws CapacidadExcedidaException {
        if (totalEmpleados >= empleados.length){
            throw new CapacidadExcedidaException(50);
        }
        
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getId().equals(e.getId())) {
                throw new DatoInvalidoException("Registrar Empleado", "ID ya existente");
            }
        }

        empleados[totalEmpleados] = e;
        totalEmpleados++;
    }
    
    public void abrirCuenta(String idCliente, Cuenta c) throws ClienteNoEncontradoException, CapacidadExcedidaException{
        Cliente clienteLocalizado = buscarCliente(idCliente);
        clienteLocalizado.agregarCuenta(c);
        cuentas[totalCuentas] = c;
        totalCuentas ++;
    }
    
    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException{
        if (id == null || id.isBlank()) {
            throw new DatoInvalidoException("ID", "Vacio");
        }
        
        Cliente clienteLocalizado = null;
        
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getId().equalsIgnoreCase(id)){
                clienteLocalizado = clientes[i];
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
        for (int i = 0; i < totalEmpleados; i++) {
            nominaTotal += empleados[i].calcularSalario();
        }
        return nominaTotal;
    }
    
    public void calcularInteresesMensuales(){
        double totalIntereses = 0;
        for (int i = 0; i < totalCuentas; i++) {
            totalIntereses += cuentas[i].calcularInteres();
            
        }
        System.out.println("Total Intereses Mensuales : " + totalIntereses);
    }
    
    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    
    @Override
    public LocalDateTime obtenerFechaCreacion() {
        return getFechaCreacion();
    }

    @Override
    public LocalDateTime obtenerUltimaModificacion() {
        return getUltimaModificacion();
    }

    @Override
    public String obtenerUsuarioModificacion() {
        return getUsuarioModificacion();
    }

    @Override
    public void registrarModificacion(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            throw new DatoInvalidoException("Usuario", "Vacio");
        }
        setUsuarioModificacion(usuario);
        setUltimaModificacion();
    }
}
