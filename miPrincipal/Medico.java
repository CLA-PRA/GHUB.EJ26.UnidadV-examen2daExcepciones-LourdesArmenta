package miPrincipal;

public class Medico extends Persona implements Identificable, Reportable, Evaluable {
    
    private String idMedico;
    private String especialidad;
    private boolean activo;
    
    public Medico(String nombre, int edad, String idMedico, String especialidad) {
        super(nombre, edad);
        
        this.idMedico = idMedico;
        this.especialidad = especialidad;
        this.activo = true;
    }
    
    @Override
    public String getId() {
        return idMedico;
    }
    
    public void setIdMedico(String idMedico) {
        
        this.idMedico = idMedico;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        
        this.especialidad = especialidad;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public void verificarEdad() throws RangoException {
        if (this.getEdad() < 25 || this.getEdad() > 75) {
            throw new RangoException("Edad no válida para un médico: " + this.getEdad());
        } else {
            System.out.println("Edad " + this.getEdad() + " está dentro del rango permitido para un médico.");
        }
    }
    
    @Override
    public boolean puedeAtender() {
        if (!activo) {
            return false;
        }
        try {
            this.verificarEdad();
            return true;
        } catch (RangoException e) {
            return false;
        }
    }
    
    @Override
    public String generarReporte() {
        return "REPORTE MÉDICO\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Edad: " + this.getEdad() + "\n" +
                "ID: " + idMedico + "\n" +
                "Especialidad: " + especialidad + "\n" +
                "Estado: " + (activo ? "Activo" : "Inactivo");
    }
    
    @Override
    public String toString() {
        return "Medico{" +
                "nombre='" + super.getNombre() + '\'' +
                ", edad=" + super.getEdad() +
                ", idMedico='" + idMedico + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", activo=" + activo +
                '}';
    }
}
