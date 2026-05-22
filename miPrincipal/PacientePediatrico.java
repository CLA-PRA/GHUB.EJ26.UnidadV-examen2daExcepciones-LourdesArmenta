package miPrincipal;

public class PacientePediatrico extends Paciente implements Diagnosticable, Reportable {

    private String nombreTutor;

    public PacientePediatrico(String nombre, int edad, String idPaciente, String nombreTutor) {
        super(nombre, edad, idPaciente);
        this.nombreTutor = nombreTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    @Override
    public String generarDiagnostico() {
        return "Diagnóstico pediátrico para " + getNombre() + " (tutor: " + nombreTutor + ").";
    }

    @Override
    public int calcularPrioridad() {
        return 2; // prioridad alta por ser menor
    }

    @Override
    public String generarReporte() {
        return "[PEDIATRICO] ID: " + getId() + " | Nombre: " + getNombre()
                + " | Edad: " + getEdad() + " | Tutor: " + nombreTutor
                + " | Prioridad: " + calcularPrioridad();
    }

    @Override
    public void verificarEdad() throws RangoException {
        if (getEdad() < 0 || getEdad() > 18) {
            throw new RangoException("Edad no válida para paciente pediátrico: " + getEdad()
                    + ". Debe estar entre 0 y 18.");
        }
        System.out.println("Paciente pediátrico " + getNombre() + " con edad " + getEdad() + " verificado.");
    }

    @Override
    public String toString() {
        return "PacientePediatrico{" + super.toString() + ", tutor='" + nombreTutor + "'}";
    }
}
