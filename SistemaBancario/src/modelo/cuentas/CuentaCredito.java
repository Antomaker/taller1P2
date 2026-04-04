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
                         double limiteCredito, double tasaInteres, double deudaActual) {
        super(numeroCuenta, saldo, usuarioModificacion);
        this.limiteCredito = limiteCredito;
        this.tasaInteres = tasaInteres;
        this.deudaActual = deudaActual;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getLimiteCredito() { return limiteCredito; }
    public double getTasaInteres() { return tasaInteres; }
    public double getDeudaActual() { return deudaActual; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }

    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return deudaActual * tasaInteres / 12; 
    }

    @Override
    public double getLimiteRetiro() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getTipoCuenta() {
        return TipoCuenta.CREDITO.toString(); 
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
    @Override
    public String obtenerResumen() {
        return "CUENTA CORRIENTE"
            + "Numero de cuenta : "+ getNumeroCuenta()+""
            + "Saldo: "+ getSaldo()
            + "Tasa de interes: " + getTasaInteres()
            + "Deuda actual: " + getDeudaActual()
            + "Limite de credito: " + getLimiteRetiro();
    }

    @Override
    public boolean estaActivo() {
        return !isBloqueada(); 
    }

    @Override
    public String obtenerTipo() {
        return "Cuenta de credito"; 
    }

    @Override
    public void depositar(double monto) throws CuentaBloqueadaException{
        verificarBloqueada();
        if (monto<=0) {
            throw new DatoInvalidoException("Depositar", monto);
            }
        deudaActual-=monto; 
        if (deudaActual<0) {
            deudaActual=0;    
        }
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException{
        verificarBloqueada();
        if (monto <=0) {
            throw new SaldoInsuficienteException(deudaActual, monto);
        } if (monto>getLimiteRetiro()) {
            throw new SaldoInsuficienteException(deudaActual, monto);
        }
        saldo+=monto; 
    }

    @Override
    public double calcularComision(double monto) {
       //FALTA CÓDIGO
       return 0;
    }

    @Override
    public double consultarSaldo() {
        return deudaActual; 
    }

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
        //FALTA CÓDIGO 
    }

    
}
