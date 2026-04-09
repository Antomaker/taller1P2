package modelo.interfaces;

public interface Notificable {
    
    // ── MÉTODOS ABSTRACTOS ───────────────────────────────────────────────────────
    void notificar(String mensaje);
    String obtenerContacto();
    boolean aceptaNotificaciones();

}
