package modelo.excepciones;

public class ClienteNoEncontradoException extends SistemaBancarioException {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String idCliente;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public ClienteNoEncontradoException(String idCliente) {

        super("[Error] Cliente no encontrado. No existe ningun cliente registrado con el ID: " + idCliente);
        this.idCliente = idCliente;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getIdCliente() {
        return idCliente;
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + "ID del cliente: " + idCliente;
    }

}
