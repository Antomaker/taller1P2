/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancariodemo.modelo.abstractas;
import com.mycompany.sistemabancariodemo.modelo.excepciones.*;
import java.time.LocalDate;
/**
 *
 * @author Dell
 */
public abstract class Persona {

private String id;
private String nombre;
private String apellido;
private LocalDate fechaNacimiento;
private String email;

public Persona(String id, String nombre, String apellido,LocalDate fechaNacimiento,String email)throws DatoInvalidoException {
    
    setId(id);
    setEmail(email);
    setFechaNacimiento(fechaNacimiento);
    this.nombre=nombre;
    this.apellido=apellido;
    
    
    
}

public abstract int calcularEdad();

public abstract String obtenerTipo();

public abstract String obtenerDocumentoDeIdentidad();

public String getNombreCompleto(){
    return nombre + " " + apellido;
}
public String getId(){
    return id;
}
public String getNombre(){
    return nombre;
}
public String getApellido(){
    return apellido;
}
public LocalDate getFechaNacimiento(){
    return fechaNacimiento;
}
public String getEmail(){
    return email;
}
public void setId(String id) throws DatoInvalidoException{
    if(id==null||id.isEmpty()){
        throw new DatoInvalidoException("id no puede ser nulo o vacio","id",id); 
}
 this.id=id;
}
public void setEmail(String email)throws DatoInvalidoException{
    if(!email.contains("@")){
        throw new DatoInvalidoException("Email debe contener @","email",email);
    }
    this.email=email;
}
public void setFechaNacimiento(LocalDate fechaNacimiento)throws DatoInvalidoException{
    if(fechaNacimiento.isAfter(LocalDate.now())){
        throw new DatoInvalidoException("Fecha no puede ser futura","Fecha de nacimiento",fechaNacimiento);
    }
    this.fechaNacimiento=fechaNacimiento;
}
}