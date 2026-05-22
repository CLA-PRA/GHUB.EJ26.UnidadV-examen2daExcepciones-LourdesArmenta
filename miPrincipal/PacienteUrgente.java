package miPrincipal;

public class PacienteUrgente extends Paciente implements Diagnosticable, Reportable {

    private String motivoUrgencia;

    public PacienteUrgente(String nombre, int edad, String idPaciente, String motivoUrgencia) {
        super(nombre, edad, idPaciente);
        this.motivoUrgencia = motivoUrgencia;
    }

    public String getMotivoUrgencia() {
        return motivoUrgencia;
    }

    @Override
    public String generarDiagnostico() {
        return "Diagnóstico urgente para " + getNombre() + ": " + motivoUrgencia;
    }

    @Override
    public int calcularPrioridad() {
        return 1; // máxima prioridad
    }

    @Override
    public String generarReporte() {
        return "[URGENTE] ID: " + getId() + " | Nombre: " + getNombre()
                + " | Edad: " + getEdad() + " | Motivo: " + motivoUrgencia
                + " | Prioridad: " + calcularPrioridad();
    }

    @Override
    public void verificarEdad() throws RangoException {
        if (getEdad() < 0 || getEdad() > 120) {
            throw new RangoException("Edad no válida para paciente urgente: " + getEdad());
        }
        System.out.println("Paciente urgente " + getNombre() + " con edad " + getEdad() + " verificado.");
    }

    @Override
    public String toString() {
        return "PacienteUrgente{" + super.toString() + ", motivoUrgencia='" + motivoUrgencia + "'}";
    }
}
