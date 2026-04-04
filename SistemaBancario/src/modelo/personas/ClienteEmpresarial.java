package modelo.personas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import modelo.abstractas.Cliente;
import modelo.excepciones.DatoInvalidoException;

public class ClienteEmpresarial extends Cliente {
    
    // ── ATRIBUTOS ────────────────────────────────────────────
    private String nit;
    private String razonSocial;
    private String representanteLegal;
    
    // ── CONSTRUCTOR ───────────────────────────────────────────────────────
    public ClienteEmpresarial(String id, String nombre, String apellido, LocalDate fechaNacimiento, String email,
                              String nit, String razonSocial, String representanteLegal) {
        super(id, nombre, apellido, fechaNacimiento, email);
        setNit(nit);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);
    }
    
    // ── GETTERS ───────────────────────────────────────────────────────
    public String getNit() { return nit; }
    public String getRazonSocial() { return razonSocial; }
    public String getRepresentanteLegal() { return representanteLegal; }
    
    // ── SETTERS ─────────────────────────────────────────────────────── 
    public void setNit(String nit) {
        if (nit == null || nit.isEmpty()) {
            throw new DatoInvalidoException("NIT", "Vacio");
        }
        this.nit = nit;
    }

    public void setRazonSocial(String razonSocial) {
        if (razonSocial == null || razonSocial.isEmpty()) {
            throw new DatoInvalidoException("Razon Social", "Vacio");
        }
        this.razonSocial = razonSocial;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        if (representanteLegal == null || representanteLegal.isEmpty()) {
            throw new DatoInvalidoException("Representante Legal", "Vacio");
        }
        this.representanteLegal = representanteLegal;
    }
    
    // ── MÉTODOS ABSTRACTOS HEREDADOS DE PERSONA ─────────────────────────────────────────────────────── 
    
    @Override
    public int calcularEdad() {
        return (int) ChronoUnit.YEARS.between(getFechaNacimiento(), LocalDate.now());
    }

    @Override
    public String obtenerTipo() {
        return "Cliente Empresarial";
    }

    @Override
    public String obtenerDocumentoIdentidad() {
        return "NIT: " + nit;
    }

}