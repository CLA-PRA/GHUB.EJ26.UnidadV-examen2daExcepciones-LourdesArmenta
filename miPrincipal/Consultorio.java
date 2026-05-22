package miPrincipal;

public class Consultorio {
    
    private String idConsultorio;
    private String nombre;
    private String especialidad;
    private int numeroPiso;
    private boolean disponible;
    
    public Consultorio(String idConsultorio, String nombre, String especialidad, int numeroPiso) throws ConsultorioException {
       
        if (numeroPiso < 0) {
            throw new ConsultorioException("El número de piso no puede ser negativo");
        }
        this.idConsultorio = idConsultorio;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.numeroPiso = numeroPiso;
        this.disponible = true;
    }
    
    public String getIdConsultorio() {
        return idConsultorio;
    }
    
    public void setIdConsultorio(String idConsultorio) {
        
        this.idConsultorio = idConsultorio;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre)  {
        
        this.nombre = nombre;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public int getNumeroPiso() {
        return numeroPiso;
    }
    
    public void setNumeroPiso(int numeroPiso) throws ConsultorioException {
        if (numeroPiso < 0) {
            throw new ConsultorioException("El número de piso no puede ser negativo");
        }
        this.numeroPiso = numeroPiso;
    }
    
    public boolean estaDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "Consultorio{" +
                "idConsultorio='" + idConsultorio + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", numeroPiso=" + numeroPiso +
                ", disponible=" + disponible +
                '}';
    }
}
