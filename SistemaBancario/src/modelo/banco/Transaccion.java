package modelo.banco;

import java.time.LocalDate;
import java.time.LocalDateTime;

import modelo.abstractas.Cuenta;
import modelo.enums.EstadoTransaccion;
import modelo.excepciones.DatoInvalidoException;

public class Transaccion {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String id;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double monto;
    private EstadoTransaccion estado;
    private final LocalDateTime fecha; // final porque no cambia
    private String descripcion;

    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Transaccion(String id, Cuenta cuentaOrigen, Cuenta cuentaDestino,
            double monto, EstadoTransaccion estado, LocalDateTime fecha,
            String descripcion) {
        setId(id);
        setCuentaOrigen(cuentaOrigen);
        setCuentaDestino(cuentaDestino);
        setMonto(monto);
        setDescripcion(descripcion);
        this.fecha = LocalDateTime.now();
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getId() { return id; }
    public Cuenta getCuentaOrigen() { return cuentaOrigen; }
    public Cuenta getCuentaDestino() { return cuentaDestino; }
    public double getMonto() { return monto; }
    public EstadoTransaccion getEstado() { return estado; }
    public LocalDateTime getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }

    // ── SETTERS ───────────────────────────────────────────────────────
    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new DatoInvalidoException("ID", "Vacio");
        }
        this.id = id;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        if (cuentaOrigen == null) {
            throw new DatoInvalidoException("Cuenta Origen", "Vacio");
        }
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        // Puede ser null en retiros/depósitos
        this.cuentaDestino = cuentaDestino;
    }

    public void setMonto(double monto) {
        if (monto < 0) {
            throw new DatoInvalidoException("Monto", monto);
        }
        this.monto = monto;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isEmpty()) {
            throw new DatoInvalidoException("Descripcion", "Vacio");
        }
        this.descripcion = descripcion;
    }

    // ── MÉTODOS ───────────────────────────────────────────────────────
    void cambiarEstado(EstadoTransaccion estado) {
        // FALTA CÓDIGO
    }

    public String generarComprobante() {
        StringBuilder sb = new StringBuilder();

        sb.append("--------------- COMPROBANTE ").append(" ---------------\n");
        sb.append("ID             : ").append(getId()).append("\n");
        sb.append("Cuenta Origen  : ").append(getCuentaOrigen()).append("\n");
        sb.append("Cuenta Destino : ").append(getCuentaDestino()).append("\n");
        sb.append("Fecha          : ").append(getFecha()).append("\n");
        sb.append("Monto          : ").append(getMonto()).append("\n");
        sb.append("Descripcion    : ").append(getDescripcion()).append("\n");
        sb.append("-------------------------------------------\n");

        return sb.toString();
    }

}
