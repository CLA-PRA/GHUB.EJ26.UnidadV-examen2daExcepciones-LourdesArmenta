package miPrincipal;
public class Paciente extends Persona implements Identificable {
    private String idPaciente;

    public Paciente(String nombre, int edad, String idPaciente) {
        super(nombre, edad);
        this.idPaciente = idPaciente;
    }

    @Override
    public String getId() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    

    @Override
    public String toString() {
        return "nombre: "+super.getNombre()+" edad: "+super.getEdad()+"id: "+ idPaciente;
    }

    @Override
    public void verificarEdad() throws RangoException {
        if (this.getEdad() < 0 || this.getEdad() > 120) {
            throw new RangoException("Edad no válida: " + this.getEdad());
        } else {
            System.out.println("Edad " + this.getEdad() + " esta dentro del rango permitido.");
        }
    }


    
}
