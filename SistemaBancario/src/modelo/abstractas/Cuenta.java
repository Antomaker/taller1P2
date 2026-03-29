package modelo.abstractas;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.banco.Transaccion;

public abstract class Cuenta {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String numeroCuenta;
    private double saldo;
    private boolean bloqueada;
    private final LocalDateTime fechaCreacion; // final porque nunca se modifica
    private LocalDateTime ultimaModificacion;
    private String usuarioModificacion;
    private Transaccion[] historial;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Cuenta(String numeroCuenta, double saldo, LocalDateTime fechaCreacion,
            LocalDateTime ultimaModificacion, String usuarioModificacion) {
        setNumeroCuenta(numeroCuenta);
        setSaldo(saldo);
        this.fechaCreacion = LocalDateTime.now();
        this.bloqueada = false; // Por defecto no está bloqueada
        this.historial = new Transaccion [20]; // Max 20 
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String numeroCuenta() { return numeroCuenta; }
    public boolean getBloqueada() { return bloqueada; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getUltimaModificacion() { return ultimaModificacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.isEmpty()) {
            throw new IllegalArgumentException("[Error] El campo no puede estar vacio");
        }
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("[Error] El saldo debe ser > 0");
        }
        this.saldo = saldo;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public void verificarBloqueada() {
        // FALTA CÓDIGO
    }

    public void agregarAlHistorial(Transaccion t) {
        // FALTA CÓDIGO
    }

    public Transaccion[] getHistorial(Transaccion t) {
        Transaccion[] copia = new Transaccion[20];
        System.arraycopy(t, 0, copia, 0, 20);
        return copia;
    }

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    public abstract double calcularInteres();
    public abstract double getLimiteRetiro();
    public abstract String getTipoCuenta();

}
