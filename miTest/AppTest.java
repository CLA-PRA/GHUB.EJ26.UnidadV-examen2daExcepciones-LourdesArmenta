package miTest;

import miPrincipal.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
   
   
    @Test
    void testMedico() {
        Medico medicoActivo = new Medico("Dra. Rivera", 40, "M001", "Cardiología");
        assertTrue(medicoActivo instanceof Persona);

        try {
            medicoActivo.verificarEdad();
        } catch (RangoException e) {
            fail("No se esperaba una excepcion para una edad valida en medico.");
        }

        assertTrue(medicoActivo.puedeAtender());
        assertEquals("M001", medicoActivo.getId());
        assertEquals("Cardiología", medicoActivo.getEspecialidad());

        String reporte = medicoActivo.generarReporte();
        assertTrue(reporte.contains("REPORTE MÉDICO"));
        assertTrue(reporte.contains("M001"));
        assertTrue(reporte.contains("Activo"));

        medicoActivo.setActivo(false);
        assertFalse(medicoActivo.puedeAtender());

        Medico medicoEdadInvalida = new Medico("Dr. Joven", 24, "M002", "Pediatría");
        try {
            medicoEdadInvalida.verificarEdad();
            fail("Se esperaba una RangoException para una edad fuera de rango en medico.");
        } catch (RangoException e) {
            assertTrue(e.getMessage().contains("médico"));
        }

        assertFalse(medicoEdadInvalida.puedeAtender());
    }

    // --- Tests polimorfismo via Persona[] ---

    @Test
    void testPolimorfismoVerificarEdad() {
        Persona[] personas = {
            new PacienteUrgente("A", 40, "U1", "Fractura"),
            new PacienteAmbulatorio("B", 22, "A1", "Rehabilitacion"),
            new PacientePediatrico("C", 5, "P1", "Tutor"),
            new Medico("Dra. Ruiz", 45, "M1", "Urgencias")
        };
        for (Persona p : personas) {
            try {
                p.verificarEdad();
            } catch (RangoException e) {
                fail(p.getClass().getSimpleName() + " lanzó excepción inesperada: " + e.getMessage());
            }
        }
    }

    @Test
    void testInterfacesBasicas()
            throws ConsultorioException, DiagnosticoException, AtencionException {

        PacienteUrgente paciente = new PacienteUrgente("Luis", 30, "P100", "Dolor torácico");
        assertTrue(paciente instanceof Diagnosticable);
        assertTrue(paciente instanceof Reportable);

        Diagnosticable diagnosticable = paciente;
        assertNotNull(diagnosticable.generarDiagnostico());
        assertEquals(1, diagnosticable.calcularPrioridad());

        Reportable reportePaciente = paciente;
        assertTrue(reportePaciente.generarReporte().contains("URGENTE"));

        Medico medico = new Medico("Dra. Vega", 45, "M100", "Cardiología");
        assertTrue(medico instanceof Identificable);
        assertTrue(medico instanceof Evaluable);
        assertTrue(medico instanceof Reportable);

        Identificable identificable = medico;
        assertEquals("M100", identificable.getId());

        Evaluable evaluable = medico;
        assertTrue(evaluable.puedeAtender());

        Reportable reporteMedico = medico;
        assertTrue(reporteMedico.generarReporte().contains("REPORTE MÉDICO"));

        Consultorio consultorio = new Consultorio("C1", "Consultorio 1", "Cardiología", 2);
        Diagnostico diagnostico = new Diagnostico("Posible angina", Diagnostico.SOSPECHOSO);
        Atencion atencion = new Atencion(medico, paciente, consultorio, diagnostico, new logica.Fecha(21, 5, 2026));

        assertTrue(atencion instanceof Registrable);
        Registrable registrable = atencion;
        String mensaje = registrable.registrar();
        assertTrue(mensaje.contains("Atención registrada exitosamente"));
        assertTrue(atencion.estaRegistrada());

        Medico medicoInactivo = new Medico("Dr. Soto", 50, "M200", "Medicina Interna");
        medicoInactivo.setActivo(false);
        Atencion atencionFallida = new Atencion(medicoInactivo, paciente, consultorio, diagnostico,
                new logica.Fecha(21, 5, 2026));

        AtencionException ex = assertThrows(AtencionException.class, atencionFallida::registrar);
        assertTrue(ex.getMessage().contains("médico no está disponible"));
    }

    @Test
    void testAtencionRegistrarExitoso()
            throws ConsultorioException, DiagnosticoException, AtencionException {

        Medico medico = new Medico("Dra. Vega", 45, "M100", "Cardiología");
        Paciente paciente = new PacienteUrgente("Luis", 30, "P100", "Dolor torácico");
        Consultorio consultorio = new Consultorio("C1", "Consultorio 1", "Cardiología", 2);
        Diagnostico diagnostico = new Diagnostico("Posible angina", Diagnostico.SOSPECHOSO);

        Atencion atencion = new Atencion(
                medico,
                paciente,
                consultorio,
                diagnostico,
                new logica.Fecha(21, 5, 2026)
        );

        String mensaje = atencion.registrar();

        assertTrue(mensaje.contains("Atención registrada exitosamente"));
        assertTrue(mensaje.contains("Médico: Dra. Vega"));
        assertTrue(mensaje.contains("Paciente: Luis"));
        assertTrue(atencion.estaRegistrada());
    }

   

    @Test
    void testRegistroBasico() throws RegistroException {
        Registro<Paciente> registro = new Registro<>("Pacientes de prueba");

        assertEquals("Pacientes de prueba", registro.getNombre());
        assertNotNull(registro.getFechaCreacion());
        assertTrue(registro.estaVacio());
        assertEquals(0, registro.contar());

        String reporte = registro.generarReporte();
        assertTrue(reporte.contains("Registro: Pacientes de prueba"));
        assertTrue(reporte.contains("Total de elementos: 0"));
        assertTrue(reporte.contains("Vacío"));
        assertTrue(reporte.contains("(ninguno)"));
    }

    @Test
    void testRegistroException() throws RegistroException {
        Registro<String> registro = new Registro<>("Registro de prueba");

        RegistroException exNulo = assertThrows(RegistroException.class, () -> registro.agregar(null));
        assertTrue(exNulo.getMessage().contains("elemento nulo"));

        registro.agregar("Elemento 1");
        RegistroException exIndice = assertThrows(RegistroException.class, () -> registro.obtener(5));
        assertTrue(exIndice.getMessage().contains("fuera de rango"));
    }
}