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

public class CuentaCorriente extends Cuenta implements Consultable, Transaccionable, Auditable {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double montoSobregiro;
    private double comisionMantenimiento;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaCorriente(String numeroCuenta, double saldo, String usuarioModificacion,
                           double montoSobregiro, double comisionMantenimiento) {
        super(numeroCuenta, saldo, usuarioModificacion);
        this.montoSobregiro = montoSobregiro;
        this.comisionMantenimiento = comisionMantenimiento;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getMontoSobregiro() { return montoSobregiro; }
    public double getComisionMantenimiento() { return comisionMantenimiento; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setMontoSobregiro(double montoSobregiro) {
        if (montoSobregiro < 0) {
            throw new DatoInvalidoException("Monto Sobregiro", montoSobregiro);     
        }
        this.montoSobregiro = montoSobregiro;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        if (comisionMantenimiento < 0) {
            throw new DatoInvalidoException("Comision de mantenimiento", comisionMantenimiento);
        }
        this.comisionMantenimiento = comisionMantenimiento;
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS ───────────────────────────────────────────────────────
    @Override
    public double calcularInteres() {
        return 0.0;
    }

    @Override //Aún no ha sido aplicado
    public double getLimiteRetiro() {
        return getSaldo() + getMontoSobregiro();
    }

    @Override
    public String getTipoCuenta() {
        return TipoCuenta.CORRIENTE.toString();
    }

    // ── MÉTODOS DE INTERFACES ───────────────────────────────────────────────────────
   
    //METODOS DE CONSULTABLE
    
    @Override
    public String obtenerResumen() {
    return "CUENTA CORRIENTE ----------------------" + "\n"
         + "Numero de cuenta : " + getNumeroCuenta() + "\n"
         + "Saldo : $" + getSaldo() + "\n"
         + "Monto sobregiro  : $" + getMontoSobregiro();}

    @Override
    public boolean estaActivo() {
       return !isBloqueada();
    }

    @Override
    public String obtenerTipo() {
       return "Cuenta Corriente";
    }

    @Override
    public void depositar(double monto) throws CuentaBloqueadaException{
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("Depositar", monto);
            }
        setSaldo(getSaldo() + monto);
    }

    //MÉTODOS DE TRANSACCIONABLE
    
    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, CuentaBloqueadaException {
        verificarBloqueada();
        if (monto <= 0) {
            throw new DatoInvalidoException("Retirar", monto);
        } if (monto > getLimiteRetiro()) {
            throw new SaldoInsuficienteException(getSaldo(), monto);
        }
        setSaldo(getSaldo() - monto);
    }

    @Override
    public double calcularComision(double monto) {
        return comisionMantenimiento;
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
//--------------------------
    @Override
    public void registrarModificacion(String usuario) {
        if (usuario == null || usuario.isBlank()) {
            throw new DatoInvalidoException("Usuario", "Vacio");
        }
        setUsuarioModificacion(usuario);
        setUltimaModificacion();
    }

}
