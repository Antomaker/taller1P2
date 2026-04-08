    
package modelo.abstractas;

import java.time.LocalDateTime;

import modelo.banco.Transaccion;
import modelo.excepciones.CapacidadExcedidaException;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

public abstract class Cuenta implements Transaccionable, Consultable, Auditable{
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String numeroCuenta;
    private double saldo; 
    private boolean bloqueada;
    private final LocalDateTime fechaCreacion; // final porque nunca se modifica
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private Transaccion[] historial;
    private int totalTransacciones;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Cuenta(String numeroCuenta, double saldo, String usuarioModificacion) {
        setNumeroCuenta(numeroCuenta);
        setSaldo(saldo);
        setUsuarioModificacion(usuarioModificacion);
        this.fechaCreacion = LocalDateTime.now();
        setUltimaModificacion();
        this.bloqueada = false; // Por defecto no está bloqueada
        this.historial = new Transaccion [20]; // Max 20 
        this.totalTransacciones = 0; // Inicia en 0
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNumeroCuenta() { return numeroCuenta; }
    public double getSaldo(){ return saldo; }
    public boolean isBloqueada() { return bloqueada; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    public int getTotalTransacciones() { return totalTransacciones; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.isEmpty()) {
            throw new DatoInvalidoException("Numero de Cuenta", "Vacio");
        }
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new DatoInvalidoException("Saldo", saldo);
        }
        this.saldo = saldo;
    }

    public void setBloqueada(boolean bloqueada) {
        if (bloqueada || !bloqueada) {
            this.bloqueada = bloqueada;
        } else {
            throw new DatoInvalidoException("Bloquear", bloqueada);
        }
    }
    
    protected void setUsuarioModificacion(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            throw new DatoInvalidoException("Usuario Modificacion", "Vacio");
        }
        this.usuarioModificacion = usuario;
        this.ultimaModificacion  = LocalDateTime.now();
    }
    
    public void setUltimaModificacion(){
        this.ultimaModificacion = LocalDateTime.now();
    }

    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public void verificarBloqueada () throws CuentaBloqueadaException {
        if (bloqueada) {
            throw new CuentaBloqueadaException();
        }
    }

    public void agregarAlHistorial(Transaccion t) throws CapacidadExcedidaException{
        if (t == null) {
            throw new DatoInvalidoException("Transaccion", "Vacio");
        }
        if (totalTransacciones >= historial.length) {
            throw new CapacidadExcedidaException(20);
        }
        historial[totalTransacciones] = t;
        totalTransacciones++;
    }

    public Transaccion[] getHistorial() {
        Transaccion[] copia = new Transaccion[20];
        System.arraycopy(historial, 0, copia, 0, totalTransacciones);
        return copia;
    }

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    public abstract double calcularInteres();
    public abstract double getLimiteRetiro();
    public abstract String getTipoCuenta();

}
