package modelo.excepciones;

public class EstadoTransaccionInvalidoException extends BancoRuntimeException {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String estadoInicial;
    private String estadoFinal;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public EstadoTransaccionInvalidoException(String estadoInicial, String estadoFinal) {
        super("[Error] Estado de transaccion invalido. No se puede pasar de: " + estadoInicial + " a " + estadoFinal);
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getEstadoInicial() {
        return estadoInicial;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "EstadoTransaccionInvalidoException" + ""
                + "Estado inicial: " + estadoInicial + " "
                + " Estado final: " + estadoFinal;

    }

}
