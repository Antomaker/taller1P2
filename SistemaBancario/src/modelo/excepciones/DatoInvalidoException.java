package modelo.excepciones;

public class DatoInvalidoException extends BancoRuntimeException {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String campo;
    private Object valorRecibido;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public DatoInvalidoException(String campo, Object valorRecibido) {
        super("[Error] Dato invalido en el campo: [" + campo + "] se ha recibido: " + valorRecibido);
        this.campo = campo;
        this.valorRecibido = valorRecibido;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getCampo() {
        return campo;
    }

    public Object getValorRecibido() {
        return valorRecibido;
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Campo: " + campo + ""
                + "Dato recibido: " + valorRecibido;
    }

}
