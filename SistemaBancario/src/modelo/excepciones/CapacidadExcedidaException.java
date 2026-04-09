package modelo.excepciones;

public class CapacidadExcedidaException extends SistemaBancarioException {

    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private int capacidadMaxima;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public CapacidadExcedidaException(int capacidadMaxima) {

        super("[Error] Ha superado la capacidad maxima de: " + capacidadMaxima);
        this.capacidadMaxima = capacidadMaxima;
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public int getCapacidadMaxima() { 
        return capacidadMaxima; 
    }

    // ── MÉTODO SOBREESCRITO ───────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + "Capacidad maxima: " + capacidadMaxima;
    }

}
