package miPrincipal;

public class ConsultorioException extends Exception {
    
    public ConsultorioException() {
        super();
    }
    
    public ConsultorioException(String mensaje) {
        super(mensaje);
    }
    
    public ConsultorioException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
