package modelo.interfaces;

import java.time.LocalDateTime;

public interface Auditable {

    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    LocalDateTime obtenerFechaCreacion();
    LocalDateTime obtenerUltimaModificacion();
    String obtenerUsuarioModificacion();
    void registrarModificacion(String usuario);
}
