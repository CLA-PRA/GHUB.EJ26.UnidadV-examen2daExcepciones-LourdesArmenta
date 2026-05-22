package miPrincipal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerPacientes {
    public static Paciente[] cargarPacientes(String archivo, int numPacientes) {
        Paciente[] pacientes = new Paciente[numPacientes];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null && index < pacientes.length) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                String idPaciente = datos[2];
                Paciente paciente = new Paciente(nombre, edad, idPaciente);
                paciente.verificarEdad();
                pacientes[index++] = paciente;
            }
        } catch (IOException | RangoException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }

        return pacientes;
    }
}
