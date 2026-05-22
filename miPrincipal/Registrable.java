package miPrincipal;

public interface Registrable {
    /**
     * Registra información en el sistema
     * @return mensaje de confirmación del registro
     */
    String registrar() throws AtencionException;
}
