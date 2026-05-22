package miPrincipal;
import logica.Fecha;

public class Diagnostico {
    
    // Constantes de tipo de diagnóstico
    public static final String SOSPECHOSO = "SOSPECHOSO";
    public static final String CONFIRMADO = "CONFIRMADO";
    
    private String descripcion;
    private String tipo;
    private Fecha fecha;
    
    public Diagnostico(String descripcion, String tipo) throws DiagnosticoException {
        
        if (!tipo.equals(SOSPECHOSO) && !tipo.equals(CONFIRMADO)) {
            throw new DiagnosticoException("El tipo de diagnóstico debe ser SOSPECHOSO o CONFIRMADO");
        }
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fecha = new Fecha(1, 1, 2000);
    }
    
    public Diagnostico(String descripcion, String tipo, Fecha fecha) throws DiagnosticoException {
        
        if (!tipo.equals(SOSPECHOSO) && !tipo.equals(CONFIRMADO)) {
            throw new DiagnosticoException("El tipo de diagnóstico debe ser SOSPECHOSO o CONFIRMADO");
        }
        if (fecha == null) {
            throw new DiagnosticoException("La fecha del diagnóstico no puede ser nula");
        }
       
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        
        this.descripcion = descripcion;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) throws DiagnosticoException {
        
        if (!tipo.equals(SOSPECHOSO) && !tipo.equals(CONFIRMADO)) {
            throw new DiagnosticoException("El tipo de diagnóstico debe ser SOSPECHOSO o CONFIRMADO");
        }
        this.tipo = tipo;
    }
    
    public Fecha getFecha() {
        return fecha;
    }
    
    public void setFecha(Fecha fecha) throws DiagnosticoException {
        if (fecha == null) {
            throw new DiagnosticoException("La fecha del diagnóstico no puede ser nula");
        }
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        return "Diagnostico{" +
                "descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", fecha=" + fecha +
                '}';
    }
}
