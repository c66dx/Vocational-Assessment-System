package Modelo;

import javax.swing.*;
import java.util.List;

public class Profession {
    private String nombre;

    public Profession(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static void showProfessionsDialog(List<String> professionNames, Area area) {
        if (professionNames != null && !professionNames.isEmpty()) {
            StringBuilder message = new StringBuilder("Profesiones en el área de " + area.getNombre() + ":\n");
            for (String professionName : professionNames) {
                message.append("- ").append(professionName).append("\n");
            }
            JOptionPane.showMessageDialog(null, message.toString(), "Profesiones", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron profesiones en el área de " + area.getNombre(), "Profesiones", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
