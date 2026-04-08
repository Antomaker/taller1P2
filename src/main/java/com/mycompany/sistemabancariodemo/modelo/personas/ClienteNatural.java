package com.mycompany.sistemabancariodemo.modelo.personas;

import com.mycompany.sistemabancariodemo.modelo.abstractas.Persona;
import java.time.LocalDate;

public class ClienteNatural extends Persona {

    public ClienteNatural(String id, String nombre, String apellido,
                          LocalDate fechaNacimiento, String email) {
        super(id, nombre, apellido, fechaNacimiento, email);
    }

    @Override
    public int calcularEdad() {
        return LocalDate.now().getYear() - getFechaNacimiento().getYear();
    }

    @Override
    public String obtenerTipo() {
        return "Cliente Natural";
    }

    @Override
    public String obtenerDocumentoDeIdentidad() {
        return getId();
    }
}
