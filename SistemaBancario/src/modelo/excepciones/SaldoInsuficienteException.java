package modelo.excepciones;

public class SaldoInsuficienteException extends SistemaBancarioException {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private double saldoActual, montoSolicitado;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public SaldoInsuficienteException(double saldoActual, double montoSolicitado) {

        super("[Error] Saldo insuficiente. Tiene disponible: " + saldoActual + " pero solicita: " + montoSolicitado);
        this.saldoActual = saldoActual;
        this.montoSolicitado = montoSolicitado;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public double getSaldoActual() {
        return saldoActual;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + "Saldo actual: " + saldoActual + "Monto solicitado: " + montoSolicitado;
    }

}
