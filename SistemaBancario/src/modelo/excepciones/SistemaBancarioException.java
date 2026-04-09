package modelo.excepciones;

import java.time.LocalDateTime;

public class SistemaBancarioException extends Exception {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String codigoError;
    private LocalDateTime timestamp;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public SistemaBancarioException(String message, String codigoError, LocalDateTime timestamp) {

        super(message);
        this.codigoError = codigoError;
        this.timestamp = timestamp;
    }

    public SistemaBancarioException(String message) {
        super(message);
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getCodigoError() {
        return codigoError;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return " Mensaje: " + getMessage() + ""
                + " CodigoError: " + getCodigoError() + ""
                + " Timestamp: " + getTimestamp();
    }

}
