package modelo.cuentas;

import java.time.LocalDateTime;

import modelo.abstractas.Cuenta;
import modelo.enums.TipoCuenta;
import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.SaldoInsuficienteException;
import modelo.interfaces.Auditable;
import modelo.interfaces.Consultable;
import modelo.interfaces.Transaccionable;

public class CuentaCredito extends Cuenta implements Consultable, Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double limiteCredito;
    private double tasaInteres;
    private double deudaActual;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaCredito(String numeroCuenta, double saldo, String usuarioModificacion,
                         double limiteCredito, double tasaInteres) {
        super(numeroCuenta, saldo, usuarioModificacion);
        this.limiteCredito = limiteCredito;
        this.tasaInteres = tasaInteres;
        this.deudaActual = 0; // Por defecto en cero
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getLimiteCredito() { return limiteCredito; }
    public double getTasaInteres() { return tasaInteres; }
    public double getDeudaActual() { return deudaActual; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setLimiteCredito(double limiteCredito) {
        if (limiteCredito<=0) {
        throw new DatoInvalidoException("limite credito", limiteCredito);    
        }
        this.limiteCredito = limiteCredito;
    }

    public void setTasaInteres(double tasaInteres) {
        if (tasaInteres<0) {
            throw new DatoInvalidoException("Tasa de interes", tasaInteres);
            }
        this.tasaInteres = tasaInteres;
    }

    public void setDeudaActual(double deudaActual) {
        if (deudaActual<0) {
            throw new DatoInvalidoException("Deuda actual", deudaActual);
                }
        this.deudaActual = deudaActual;
    }

    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return (deudaActual * tasaInteres) / 12; 
    }

    @Override
    public double getLimiteRetiro() {
        return getLimiteCredito() - getDeudaActual();
    }

    @Override
    public String getTipoCuenta() {
        return TipoCuenta.CREDITO.toString(); 
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    
    //MÉTODOS DE CONSULTABLE
    @Override
    public String obtenerResumen() {
        return "CUENTA CREDITO ------------------------" + "\n"
             + "Numero de cuenta : " + getNumeroCuenta() + "\n"
             + "Saldo : $" + getSaldo() + "\n"
             + "Tasa de interes : " + getTasaInteres() + "\n"
             + "Deuda actual : $" + getDeudaActual() + "\n"
             + "Limite de credito : $" + getLimiteRetiro();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada(); 
    }

    @Override
    public String obtenerTipo() {
        return "Cuenta de credito"; 
    }

    //MÉTODOS DE TRANSACCIONABLE
    @Override
    public void depositar(double monto) throws CuentaBloqueadaException{
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("Depositar", monto);
            }
        deudaActual -= monto; 
        if (deudaActual < 0) {
            deudaActual = 0;    
        }
    }

    @Override
    public void retirar(double monto) throws CuentaBloqueadaException{
        verificarBloqueada();
        if (monto > getLimiteRetiro()) {
            throw new DatoInvalidoException("Retirar", monto);
        }
        deudaActual += monto;
    }

    @Override
    public double calcularComision(double monto) {
       //Comisión por operación 
       return monto * 0.02;
    }

    @Override
    public double consultarSaldo() {
        return deudaActual; 
    }

    
    //MÉTODOS DE AUDITABLE
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
