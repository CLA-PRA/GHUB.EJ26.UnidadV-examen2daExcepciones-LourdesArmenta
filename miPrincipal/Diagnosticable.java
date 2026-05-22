package miPrincipal;

public interface Diagnosticable {
    String generarDiagnostico();
    int calcularPrioridad(); // 1 = urgente, 5 = leve
}
