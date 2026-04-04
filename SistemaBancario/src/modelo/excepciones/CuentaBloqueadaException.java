package modelo.excepciones;

import java.time.LocalDateTime;

public class CuentaBloqueadaException extends SistemaBancarioException {

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CuentaBloqueadaException() {
        super("[Error] Esta cuenta se encuentra bloqueada, no puede realizar operaciones");
    }

    public CuentaBloqueadaException(String message, String codigoError, LocalDateTime timestamp) {
        super(message, codigoError, timestamp);
    }

    public CuentaBloqueadaException(String message) {
        super(message);
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString();
    }

}
