package modelo.excepciones;

public class BancoRuntimeException extends RuntimeException {

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public BancoRuntimeException(String message) {
        super(message);
    }
    

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "mensaje: " + getMessage();
    }

}
