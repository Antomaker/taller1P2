package modelo.banco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import modelo.abstractas.Cuenta;
import modelo.enums.EstadoTransaccion;
import modelo.excepciones.DatoInvalidoException;
import modelo.excepciones.EstadoTransaccionInvalidoException;

public class Transaccion {
    // Para formatear las fechas y horas
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
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
            double monto, EstadoTransaccion estado, String descripcion) {
        setId(id);
        setCuentaOrigen(cuentaOrigen);
        setCuentaDestino(cuentaDestino);
        setMonto(monto);
        setDescripcion(descripcion);
        this.fecha = LocalDateTime.now();
        this.estado = estado;
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
        if (monto <= 0) {
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
    public void cambiarEstado(EstadoTransaccion nuevo) {
        switch (getEstado()) {
            case PENDIENTE -> {
                if (nuevo == EstadoTransaccion.PROCESANDO || nuevo == EstadoTransaccion.RECHAZADA) {
                    this.estado = nuevo;
                    return;
                } else {
                    throw new EstadoTransaccionInvalidoException(getEstado().toString(), nuevo.toString());
                }
            }
            case PROCESANDO -> {
                if (nuevo == EstadoTransaccion.COMPLETADA || nuevo == EstadoTransaccion.RECHAZADA) {
                    this.estado = nuevo;
                    return;
                } else {
                    throw new EstadoTransaccionInvalidoException(getEstado().toString(), nuevo.toString());
                }
            } 
            case COMPLETADA -> {
                if (nuevo == EstadoTransaccion.REVERTIDA) {
                    this.estado = nuevo;
                    return;
                } else {
                     throw new EstadoTransaccionInvalidoException(getEstado().toString(), nuevo.toString());
                }
            }
            case RECHAZADA, REVERTIDA -> throw new EstadoTransaccionInvalidoException(getEstado().toString(), nuevo.toString());
        }
    }

    public String generarComprobante() {
        StringBuilder sb = new StringBuilder();
        
        String destino = (cuentaDestino != null) ? cuentaDestino.getNumeroCuenta() : "N/A";

        sb.append("------------- COMPROBANTE ").append("-------------\n");
        sb.append("ID             : ").append(getId()).append("\n");
        sb.append("Cuenta Origen  : ").append(getCuentaOrigen().getNumeroCuenta()).append("\n");
        sb.append("Cuenta Destino : ").append(destino).append("\n");
        sb.append("Fecha          : ").append(getFecha().format(formato)).append("\n");
        sb.append("Monto          : ").append(getMonto()).append("\n");
        sb.append("Descripcion    : ").append(getDescripcion()).append("\n");
        sb.append("---------------------------------------");

        return sb.toString();
    }

}
