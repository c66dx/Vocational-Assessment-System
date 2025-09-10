package Modelo;

/**
 * Representa una profesión dentro de un área específica
 * 
 * @author Tu Nombre
 * @version 1.0
 * @since 2024
 */
public class Profession {
    private final String nombre;

    /**
     * Constructor para crear una nueva profesión
     * 
     * @param nombre El nombre de la profesión
     */
    public Profession(String nombre) {
        this.nombre = nombre != null ? nombre.trim() : "";
    }

    /**
     * Obtiene el nombre de la profesión
     * 
     * @return El nombre de la profesión
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Verifica si la profesión tiene un nombre válido
     * 
     * @return true si el nombre no está vacío, false en caso contrario
     */
    public boolean isValid() {
        return !nombre.isEmpty();
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profession that = (Profession) obj;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
