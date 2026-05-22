package miPrincipal;

public class DiagnosticoException extends Exception {
    
    public DiagnosticoException() {
        super();
    }
    
    public DiagnosticoException(String mensaje) {
        super(mensaje);
    }
    
    public DiagnosticoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
