package miPrincipal;

import logica.Fecha;

//La clase debe ser genérica
public class Registro {
    
    //crea un arreglo tipo T de nombre elementos
    
    private String nombre;
    private Fecha fechaCreacion;
    private int cantidad;
    
   
    public Registro(String nombre) {
        this.nombre = nombre;
        this.elementos = (T[]) new Object[10];
        this.fechaCreacion = Fecha.hoy();
        this.cantidad = 0;
    }
    
    
    public void agregar(T elemento) throws RegistroException {
        if (elemento == null) {
            throw new RegistroException("No se puede registrar un elemento nulo en " + nombre);
        }
        if (cantidad == elementos.length) {
            T[] nuevoArreglo = (T[]) new Object[elementos.length * 2];
            for (int i = 0; i < elementos.length; i++) {
                nuevoArreglo[i] = elementos[i];
            }
            elementos = nuevoArreglo;
        }
        elementos[cantidad++] = elemento;
    }
    
   
    public T obtener(int indice) throws RegistroException {
        if (indice < 0 || indice >= cantidad) {
            throw new RegistroException("Índice fuera de rango: " + indice + " en " + nombre);
        }
        return elementos[indice];
    }
    
   
    public T eliminar(int indice) throws RegistroException {
        if (indice < 0 || indice >= cantidad) {
            throw new RegistroException("Índice fuera de rango: " + indice + " en " + nombre);
        }
        T eliminado = elementos[indice];
        for (int i = indice; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[cantidad - 1] = null;
        cantidad--;
        return eliminado;
    }
    
    
    public Object[] obtenerTodos() {
        Object[] copia = new Object[cantidad];
        for (int i = 0; i < cantidad; i++) {
            copia[i] = elementos[i];
        }
        return copia;
    }
    
    
    public int contar() {
        return cantidad;
    }
    
   
    public boolean estaVacio() {
        return cantidad == 0;
    }
    
    
    public void limpiar() {
        for (int i = 0; i < cantidad; i++) {
            elementos[i] = null;
        }
        cantidad = 0;
    }
    
   
    public boolean contiene(T elemento) {
        for (int i = 0; i < cantidad; i++) {
            if (elementos[i] == null ? elemento == null : elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public Fecha getFechaCreacion() {
        return fechaCreacion;
    }
    
    
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== Registro: ").append(nombre).append(" ===\n")
                .append("Fecha de creación: ").append(fechaCreacion).append("\n")
                .append("Total de elementos: ").append(contar()).append("\n")
                .append("Estado: ").append(estaVacio() ? "Vacío" : "Con datos").append("\n")
                .append("Elementos registrados:\n");
        
        if (estaVacio()) {
            reporte.append("  (ninguno)\n");
            return reporte.toString();
        }

        for (int i = 0; i < cantidad; i++) {
            reporte.append("  [").append(i).append("] ").append(elementos[i]).append("\n");
        }
        
        return reporte.toString();
    }
    
    @Override
    public String toString() {
        return "Registro{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + contar() +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
