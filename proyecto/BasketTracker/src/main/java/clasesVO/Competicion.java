public class Competicion {
    private String nombre;

    // Constructor
    public Competicion(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Competicion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
