package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un área vocacional con sus profesiones asociadas
 * 
 * @author Tu Nombre
 * @version 1.0
 * @since 2024
 */
public class Area {
    private final String nombre;
    private final List<Profession> profesiones;

    /**
     * Constructor para crear una nueva área vocacional
     * 
     * @param nombre El nombre del área
     * @param profesiones Lista de profesiones asociadas al área
     */
    public Area(String nombre, List<Profession> profesiones) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.profesiones = profesiones != null ? new ArrayList<>(profesiones) : new ArrayList<>();
    }

    /**
     * Obtiene el nombre del área
     * 
     * @return El nombre del área
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de profesiones del área
     * 
     * @return Lista inmutable de profesiones
     */
    public List<Profession> getProfesiones() {
        return new ArrayList<>(profesiones);
    }

    /**
     * Obtiene el número de profesiones en el área
     * 
     * @return Cantidad de profesiones
     */
    public int getCantidadProfesiones() {
        return profesiones.size();
    }

    /**
     * Verifica si el área tiene profesiones asociadas
     * 
     * @return true si tiene profesiones, false en caso contrario
     */
    public boolean tieneProfesiones() {
        return !profesiones.isEmpty();
    }

    /**
     * Obtiene el nombre del área formateado para mostrar
     * 
     * @return Nombre del área con espacios en lugar de guiones bajos
     */
    public String getNombreFormateado() {
        return nombre.replace("_", " ");
    }

    @Override
    public String toString() {
        return String.format("Area{nombre='%s', profesiones=%d}", nombre, profesiones.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Area area = (Area) obj;
        return nombre.equals(area.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
}
