package miPrincipal;
import logica.Fecha;

public class Atencion implements Registrable {
    
    private Medico medico;
    private Paciente paciente;
    private Consultorio consultorio;
    private Diagnostico diagnostico;
    private Fecha fecha;
    private boolean registrada;
    
    public Atencion(Medico medico, Paciente paciente, Consultorio consultorio, 
                    Diagnostico diagnostico, Fecha fecha)  {
       
        
        this.medico = medico;
        this.paciente = paciente;
        this.consultorio = consultorio;
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.registrada = false;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public void setMedico(Medico medico)  {
       
        this.medico = medico;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
       
        this.paciente = paciente;
    }
    
    public Consultorio getConsultorio() {
        return consultorio;
    }
    
    public void setConsultorio(Consultorio consultorio)  {
        
        this.consultorio = consultorio;
    }
    
    public Diagnostico getDiagnostico() {
        return diagnostico;
    }
    
    public void setDiagnostico(Diagnostico diagnostico)  {
       
        this.diagnostico = diagnostico;
    }
    
    public Fecha getFecha() {
        return fecha;
    }
    
    public void setFecha(Fecha fecha)  {
       
        this.fecha = fecha;
    }
    
    public boolean estaRegistrada() {
        return registrada;
    }
    
    @Override
    public String registrar() throws AtencionException {
        // Validar que el médico pueda atender
        if (!medico.puedeAtender()) {
            throw new AtencionException("El médico no está disponible para atender");
        }
        
        if (!consultorio.estaDisponible()) {
            throw new AtencionException("El consultorio no está disponible");
        }
        
        this.registrada = true;
        return "Atención registrada exitosamente:\n" +
                "Médico: " + medico.getNombre() + "\n" +
                "Paciente: " + paciente.getNombre() + "\n" +
                "Consultorio: " + consultorio.getNombre() + "\n" +
            "Fecha: " + fecha;
    }
    
    @Override
    public String toString() {
        return "Atencion{" +
                "medico=" + medico.getNombre() +
                ", paciente=" + paciente.getNombre() +
                ", consultorio=" + consultorio.getNombre() +
                ", diagnostico=" + diagnostico.getDescripcion() +
            ", fecha=" + fecha +
                ", registrada=" + registrada +
                '}';
    }
}
