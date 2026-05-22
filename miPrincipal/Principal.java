package miPrincipal;

import java.time.LocalDate;
import logica.Fecha;

public class Principal {

    public static void main(String[] args) {

        // --- Carga desde archivo (polimorfismo base) ---
        Paciente[] pacientes = LeerPacientes.cargarPacientes("pacientes.txt", 10);

        System.out.println("=== Pacientes cargados desde archivo ===");
        for (Paciente paciente : pacientes) {
            if (paciente != null) {
                System.out.println("Paciente creado: " + paciente);
            }
        }

        // --- Polimorfismo con Persona[], Diagnosticable y Reportable ---
        System.out.println("\n=== Creación de Pacientes polimórficos ===");
        try {
            Persona[] personas = {
                new PacienteUrgente("Laura", 45, "U001", "Infarto"),
                new PacienteAmbulatorio("Carlos", 30, "A001", "Fisioterapia"),
                new PacientePediatrico("Sofia", 8, "P001", "Elena García")
            };

            
        } catch (Exception e) {
            System.out.println("Error al crear pacientes: " + e.getMessage());
            e.printStackTrace();
        }

        // --- NUEVAS FUNCIONALIDADES: Médicos, Consultorios y Atenciones ---
        System.out.println("\n=== NUEVAS FUNCIONALIDADES ===");
        
        System.out.println("\n=== Creación de Médicos ===");
        try {
            Medico medico1 = new Medico("Dr. Juan Pérez", 45, "M001", "Cardiología");
            Medico medico2 = new Medico("Dra. María López", 38, "M002", "Pediatría");
            
            System.out.println(medico1);
            System.out.println(medico2);
            
            System.out.println("\n=== Reportes de Médicos ===");
            System.out.println(medico1.generarReporte());
            System.out.println();
            System.out.println(medico2.generarReporte());
            
            System.out.println("\n=== Verificación de disponibilidad de Médicos ===");
            System.out.println("¿Puede atender médico 1? " + medico1.puedeAtender());
            System.out.println("¿Puede atender médico 2? " + medico2.puedeAtender());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error con el médico: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Creación de Consultorios ===");
        try {
            Consultorio consultorio1 = new Consultorio("C001", "Cardiología A", "Cardiología", 2);
            Consultorio consultorio2 = new Consultorio("C002", "Pediatría B", "Pediatría", 1);
            
            System.out.println(consultorio1);
            System.out.println(consultorio2);
        } catch (ConsultorioException e) {
            System.out.println("Error con el consultorio: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Creación de Diagnósticos ===");
        try {
            Diagnostico diagnostico1 = new Diagnostico("Presión arterial elevada", Diagnostico.SOSPECHOSO);
            Diagnostico diagnostico2 = new Diagnostico("Hipertensión confirmada", Diagnostico.CONFIRMADO);
            
            System.out.println(diagnostico1);
            System.out.println(diagnostico2);
        } catch (DiagnosticoException e) {
            System.out.println("Error con el diagnóstico: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Registro de Atenciones Médicas ===");
        try {
            Medico medico = new Medico("Dr. Juan Pérez", 45, "M001", "Cardiología");
            medico.verificarEdad();
            
            Paciente paciente = new Paciente("Roberto García", 55, "P001");
            paciente.verificarEdad();
            
            Consultorio consultorio = new Consultorio("C001", "Cardiología A", "Cardiología", 2);
            Diagnostico diagnostico = new Diagnostico("Presión arterial elevada", Diagnostico.CONFIRMADO);
            
            Atencion atencion1 = new Atencion(
                medico,
                paciente,
                consultorio,
                diagnostico,
                new Fecha(10, 5, 2026)
            );
            
            System.out.println(atencion1);
            
            // Registrar la atención
            System.out.println("\n=== Registrando Atención ===");
            String mensajeRegistro = atencion1.registrar();
            System.out.println(mensajeRegistro);
            
        } catch (RangoException e) {
            System.out.println("Error de rango en médico o paciente: " + e.getMessage());
            e.printStackTrace();
        } catch (ConsultorioException e) {
            System.out.println("Error del consultorio durante la atención: " + e.getMessage());
            e.printStackTrace();
        } catch (DiagnosticoException e) {
            System.out.println("Error en el diagnóstico de la atención: " + e.getMessage());
            e.printStackTrace();
        } catch (AtencionException e) {
            System.out.println("Error al registrar la atención: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Prueba de manejo de excepciones ===");
        
        // Prueba: Médico con edad inválida
        System.out.println("\nPrueba 1: Médico con edad inválida");
        try {
            Medico medicoInvalido = new Medico("Dr. Inválido", 80, "M999", "Medicina General");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado: " + e.getMessage());
        }
        
        // Prueba: Consultorio con piso negativo
        System.out.println("\nPrueba 2: Consultorio con piso negativo");
        try {
            Consultorio consultorioInvalido = new Consultorio("C999", "Consultorio Inválido", "Medicina General", -1);
        } catch (ConsultorioException e) {
            System.out.println("Capturado: " + e.getMessage());
        }
        
        // Prueba: Diagnóstico con descripción vacía
        System.out.println("\nPrueba 3: Diagnóstico con descripción vacía");
        try {
            Diagnostico diagnosticoInvalido = new Diagnostico("", Diagnostico.CONFIRMADO);
        } catch (DiagnosticoException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        System.out.println("\n=== CLASES GENÉRICAS - Registro<T> ===");
        
        // Registro de Pacientes
        System.out.println("\n=== Registro de Pacientes ===");
        try {
            Registro<Paciente> registroPacientes = new Registro<>("Pacientes del Hospital");
            
            Paciente p1 = new Paciente("Roberto García", 55, "P001");
            Paciente p2 = new Paciente("Ana Martínez", 42, "P002");
            Paciente p3 = new Paciente("Carlos López", 38, "P003");
            
            registroPacientes.agregar(p1);
            registroPacientes.agregar(p2);
            registroPacientes.agregar(p3);
            
            System.out.println(registroPacientes.generarReporte());
            System.out.println("Total de pacientes: " + registroPacientes.contar());
            
        } catch (RegistroException e) {
            System.out.println("Error en registro de pacientes: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Registro de Médicos
        System.out.println("\n=== Registro de Médicos ===");
        try {
            Registro<Medico> registroMedicos = new Registro<>("Médicos del Hospital");
            
            Medico m1 = new Medico("Dr. Juan Pérez", 45, "M001", "Cardiología");
            Medico m2 = new Medico("Dra. María López", 38, "M002", "Pediatría");
            Medico m3 = new Medico("Dr. Carlos Ruiz", 52, "M003", "Cirugía");
            
            registroMedicos.agregar(m1);
            registroMedicos.agregar(m2);
            registroMedicos.agregar(m3);
            
            System.out.println(registroMedicos.generarReporte());
            System.out.println("Total de médicos: " + registroMedicos.contar());
            System.out.println("Obtener médico en índice 1: " + registroMedicos.obtener(1));
            
        } catch (RegistroException e) {
            System.out.println("Error en registro de médicos: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Registro de Consultorios
        System.out.println("\n=== Registro de Consultorios ===");
        try {
            Registro<Consultorio> registroConsultorios = new Registro<>("Consultorios del Hospital");
            
            Consultorio c1 = new Consultorio("C001", "Cardiología A", "Cardiología", 2);
            Consultorio c2 = new Consultorio("C002", "Pediatría B", "Pediatría", 1);
            Consultorio c3 = new Consultorio("C003", "Cirugía C", "Cirugía", 3);
            
            registroConsultorios.agregar(c1);
            registroConsultorios.agregar(c2);
            registroConsultorios.agregar(c3);
            
            System.out.println(registroConsultorios.generarReporte());
            System.out.println("Total de consultorios: " + registroConsultorios.contar());
            System.out.println("¿Contiene consultorio c2? " + registroConsultorios.contiene(c2));
            
        } catch (ConsultorioException | RegistroException e) {
            System.out.println("Error en registro de consultorios: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Registro de Diagnósticos
        System.out.println("\n=== Registro de Diagnósticos ===");
        try {
            Registro<Diagnostico> registroDiagnosticos = new Registro<>("Diagnósticos Realizados");
            
            Diagnostico d1 = new Diagnostico("Hipertensión", Diagnostico.CONFIRMADO);
            Diagnostico d2 = new Diagnostico("Diabetes", Diagnostico.CONFIRMADO);
            Diagnostico d3 = new Diagnostico("Infecciones respiratorias", Diagnostico.SOSPECHOSO);
            
            registroDiagnosticos.agregar(d1);
            registroDiagnosticos.agregar(d2);
            registroDiagnosticos.agregar(d3);
            
            System.out.println(registroDiagnosticos.generarReporte());
            System.out.println("Total de diagnósticos: " + registroDiagnosticos.contar());
            
        } catch (DiagnosticoException | RegistroException e) {
            System.out.println("Error en registro de diagnósticos: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Pruebas de excepciones con Registro
        System.out.println("\n=== Pruebas de Excepciones - Registro ===");
        try {
            Registro<String> registroSimple = new Registro<>("Registro Simple");
            registroSimple.agregar(null);  // Esto lanzará excepción
        } catch (RegistroException e) {
            System.out.println("Capturado: " + e.getMessage());
        }
        
        try {
            Registro<String> registroSimple = new Registro<>("Registro Simple");
            registroSimple.agregar("Elemento 1");
            registroSimple.obtener(5);  // Índice fuera de rango
        } catch (RegistroException e) {
            System.out.println("Capturado: " + e.getMessage());
        }

        // --- Flujo alineado con pruebas unitarias ---
        System.out.println("\n=== Flujo principal alineado con AppTest ===");
        try {
            Medico medico = new Medico("Dra. Vega", 45, "M100", "Cardiología");
            Paciente paciente = new PacienteUrgente("Luis", 30, "P100", "Dolor torácico");
            Consultorio consultorio = new Consultorio("C1", "Consultorio 1", "Cardiología", 2);
            Diagnostico diagnostico = new Diagnostico("Posible angina", Diagnostico.SOSPECHOSO);

            Atencion atencion = new Atencion(medico, paciente, consultorio, diagnostico, new Fecha(21, 5, 2026));
            System.out.println("Médico puede atender: " + medico.puedeAtender());
            System.out.println(atencion.registrar());

            Registro<Paciente> registroBasico = new Registro<>("Pacientes de prueba");
            System.out.println("Registro vacío: " + registroBasico.estaVacio());
            System.out.println("Total en registro básico: " + registroBasico.contar());

        } catch (ConsultorioException e) {
            System.out.println("Error de consultorio en flujo alineado: " + e.getMessage());
            e.printStackTrace();
        } catch (DiagnosticoException e) {
            System.out.println("Error de diagnóstico en flujo alineado: " + e.getMessage());
            e.printStackTrace();
        } catch (AtencionException e) {
            System.out.println("Error al registrar atención en flujo alineado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Programa terminado. ===");
    }
}