package miPrincipal;

public class RegistroException extends Exception {
    
    public RegistroException() {
        super();
    }
    
    public RegistroException(String mensaje) {
        super(mensaje);
    }
    
    public RegistroException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
