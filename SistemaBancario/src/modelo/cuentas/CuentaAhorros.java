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
                         double tasaInteres, int retirosMesActual, int maxRetirosMes) {
        super(numeroCuenta, saldo, usuarioModificacion);
        this.tasaInteres = tasaInteres;
        this.retirosMesActual = retirosMesActual;
        this.maxRetirosMes = maxRetirosMes;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getTasaInteres() { return tasaInteres; }
    public int getRetirosMesActual() { return retirosMesActual; }
    public int getMaxRetirosMes() { return maxRetirosMes; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setRetirosMesActual(int retirosMesActual) {
        this.retirosMesActual = retirosMesActual;
    }

    public void setMaxRetirosmes(int maxRetirosMes) {
        this.maxRetirosMes = maxRetirosMes;
    }

    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return (getSaldo() * tasaInteres) / 12;
    }

    @Override
    public double getLimiteRetiro() {
        //Falta por colocar
        return 0;
    }

    @Override
    public String getTipoCuenta() {
        return TipoCuenta.AHORROS.toString();
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
   
    //MÉTODOS DE CONSULTABLE
    
    @Override
    public String obtenerResumen() {
        return "CUENTA DE AHORROS"+
                "Numero de cuenta: " + getNumeroCuenta() +
                "Saldo" + getSaldo() + 
                "Tasa de interes"+ getTasaInteres()+
                "Retiros realizados este mes: " + getRetirosMesActual() + 
                "Maximo de retiros posibles: " + getMaxRetirosMes(); 
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada(); 
    }

    @Override
    public String obtenerTipo() {
        return "Cuenta de ahorros";}

    //MÉTODOS DE TRANSACCIONABLE
    
    @Override
    public void depositar(double monto) throws CuentaBloqueadaException{
         verificarBloqueada();
        if (monto<=0) {
            throw new DatoInvalidoException("Depositar", monto);
            }
        saldo+=monto; 
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException{
            verificarBloqueada();
        if (monto <=0) {
            throw new SaldoInsuficienteException(saldo, monto);
        } if (monto>getLimiteRetiro()) {
            throw new SaldoInsuficienteException(saldo, monto);
        }
        saldo-=monto;
        retirosMesActual++;
       
    }

    @Override
    public double calcularComision(double monto) {
        //FALTA CÓDIGO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double consultarSaldo() {
    return saldo;
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
       //Proximo a editar 
    }

}
