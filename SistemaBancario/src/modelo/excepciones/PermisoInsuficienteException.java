package modelo.excepciones;

public class PermisoInsuficienteException extends BancoRuntimeException {

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public PermisoInsuficienteException() {
        super("[Error] El usuario no tiene los permisos suficientes para realizar la operacion");
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString();
    }

}
