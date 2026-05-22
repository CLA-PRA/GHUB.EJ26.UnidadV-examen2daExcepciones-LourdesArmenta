package miPrincipal;

public class PacienteAmbulatorio extends Paciente implements Diagnosticable, Reportable {

    private String tratamiento;

    public PacienteAmbulatorio(String nombre, int edad, String idPaciente, String tratamiento) {
        super(nombre, edad, idPaciente);
        this.tratamiento = tratamiento;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    @Override
    public String generarDiagnostico() {
        return "Diagnóstico ambulatorio para " + getNombre() + ": tratamiento '" + tratamiento + "' en curso.";
    }

    @Override
    public int calcularPrioridad() {
        return 4; // prioridad baja
    }

    @Override
    public String generarReporte() {
        return "[AMBULATORIO] ID: " + getId() + " | Nombre: " + getNombre()
                + " | Edad: " + getEdad() + " | Tratamiento: " + tratamiento
                + " | Prioridad: " + calcularPrioridad();
    }

    @Override
    public void verificarEdad() throws RangoException {
        if (getEdad() < 0 || getEdad() > 120) {
            throw new RangoException("Edad no válida para paciente ambulatorio: " + getEdad());
        }
        System.out.println("Paciente ambulatorio " + getNombre() + " con edad " + getEdad() + " verificado.");
    }

    @Override
    public String toString() {
        return "PacienteAmbulatorio{" + super.toString() + ", tratamiento='" + tratamiento + "'}";
    }
}
