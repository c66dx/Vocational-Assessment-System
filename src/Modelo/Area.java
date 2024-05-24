package Modelo;

import java.util.List;

public class Area {
    private String nombre;
    private List<Profession> profesiones;

    public Area(String nombre, List<Profession> profesiones) {
        this.nombre = nombre;
        this.profesiones = profesiones;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Profession> getProfesiones() {
        return profesiones;
    }
}
