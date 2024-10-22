package clasesVO;

public class CompeticionVO {
    private String nombre;

    // Constructor
    public CompeticionVO(String nombre) {
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
