package modelo.abstractas;

import java.time.LocalDate;
import modelo.excepciones.DatoInvalidoException;

public abstract class Persona {
    
    // ── ATRIBUTOS ───────────────────────────────────────────────────────
    private String id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public Persona(String id, String nombre, String apellido,
            LocalDate fechaNacimiento, String email) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setFechaNacimiento(fechaNacimiento);
        setEmail(email);
    }

    // ── GETTERS ───────────────────────────────────────────────────────
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getEmail() { return email; }
    
    // ── SETTERS ───────────────────────────────────────────────────────
    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new DatoInvalidoException("ID", "Vacio");
        }
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new DatoInvalidoException("Nombre", "Vacio");
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new DatoInvalidoException("Apellido", "Vacio");
        }
        this.apellido = apellido;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new DatoInvalidoException("Fecha de Nacimiento", "Fecha futura");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new DatoInvalidoException("Email", "Email invalido");
        }
        this.email = email;
    }

    // ── MÉTODOS CONCRETOS ───────────────────────────────────────────────────────
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    public abstract int calcularEdad();
    public abstract String obtenerTipo();
    public abstract String obtenerDocumentoIdentidad();

}
