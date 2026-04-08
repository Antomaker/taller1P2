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

public class CuentaAhorros extends Cuenta implements Consultable, Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double tasaInteres;
    private int retirosMesActual, maxRetirosMes;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaAhorros(String numeroCuenta, double saldo, String usuarioModificacion,
                         double tasaInteres, int maxRetirosMes) {
        super(numeroCuenta, saldo, usuarioModificacion);
        this.tasaInteres = tasaInteres;
        this.retirosMesActual = 0;
        this.maxRetirosMes = maxRetirosMes;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getTasaInteres() { return tasaInteres; }
    public int getRetirosMesActual() { return retirosMesActual; }
    public int getMaxRetirosMes() { return maxRetirosMes; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setTasaInteres(double tasaInteres) {
        if (tasaInteres < 0) {
            throw new DatoInvalidoException("Tasa de interes", tasaInteres); 
        }
        this.tasaInteres = tasaInteres;
    }

    public void setMaxRetirosmes(int maxRetirosMes) {
        if (maxRetirosMes < 0) {
            throw new DatoInvalidoException("Maximo de retiros mensuales", maxRetirosMes);
            }
        this.maxRetirosMes = maxRetirosMes;
    }

    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return (getSaldo() * tasaInteres) / 12;
    }

    @Override
    public double getLimiteRetiro() {
        return getSaldo();
    }

    @Override
    public String getTipoCuenta() {
        return TipoCuenta.AHORROS.toString();
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
   
    //MÉTODOS DE CONSULTABLE
    @Override
    public String obtenerResumen() {
        return "CUENTA DE AHORROS ---------------------" + "\n"
             + "Numero de cuenta : " + getNumeroCuenta() + "\n"
             + "Saldo : " + getSaldo() + "\n"
             + "Tasa de interes  : "+ getTasaInteres() + "\n"
             + "Retiros realizados este mes : " + getRetirosMesActual() + "\n"
             + "Maximo de retiros posibles  : " + getMaxRetirosMes(); 
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada(); 
    }

    @Override
    public String obtenerTipo() {
        return "Cuenta de ahorros";
    }

    //MÉTODOS DE TRANSACCIONABLE
    @Override
    public void depositar(double monto) throws CuentaBloqueadaException{
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("Depositar", monto);
        }
        setSaldo(getSaldo() + monto);
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException{
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("Retirar", monto);
        } if (monto > getLimiteRetiro()) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }
        setSaldo(getSaldo() - monto);
        retirosMesActual++;
    }

    @Override
    public double calcularComision(double monto) {
        /*Las cuentas de ahorros generalmente no cobran comisión si se mantiene 
        dentro de un número de retiros, y se cobran si excede el límite*/
        if (retirosMesActual > maxRetirosMes) {
            return monto * 0.02; 
        }
        return 0;
    }

    @Override
    public double consultarSaldo() {
        return getSaldo();
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
