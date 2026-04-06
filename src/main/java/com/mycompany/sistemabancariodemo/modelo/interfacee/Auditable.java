
package com.mycompany.sistemabancariodemo.modelo.interfacee;
import java.time.LocalDateTime;

public interface Auditable {
    
    public LocalDateTime obtenerFechaDeCreacion();
    
    public LocalDateTime obtenerUltimaModificacion();
    
    public String obtenerUsuarioModificacion();
    
    public void registrarModificacion(String usuario);  
}
