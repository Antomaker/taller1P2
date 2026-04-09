package modelo.interfaces;

import modelo.excepciones.CuentaBloqueadaException;
import modelo.excepciones.SaldoInsuficienteException;

public interface Transaccionable {

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    void depositar(double monto) throws CuentaBloqueadaException;
    void retirar(double monto)throws SaldoInsuficienteException, CuentaBloqueadaException;
    double calcularComision(double monto);
    double consultarSaldo();

}
