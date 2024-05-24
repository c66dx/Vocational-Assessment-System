package Vista;

import Controlador.TestVocationalLogic;
import Modelo.Area;
import Modelo.Profession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ResultsGUI {
    private final JFrame frame;
    private final Map<String, Integer> results;
    private final List<Area> areas;
    private final TestVocationalLogic logic;

    private final Color backgroundColor = Color.decode("#F5CBA7"); // Color de fondo

    public ResultsGUI(Map<String, Integer> results, List<Area> areas, TestVocationalLogic logic) {
        this.results = results;
        this.areas = areas;
        this.logic = logic;
        frame = new JFrame("Resultados del Test Vocacional");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Tamaño inicial de la ventana
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(backgroundColor);
        frame.setLocationRelativeTo(null);
    }

    public void showResults() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        panel.setBackground(backgroundColor);

        JLabel titleLabel = new JLabel("Resultados del Test Vocacional");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(45, 45, 45));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Construir el mensaje para los resultados principales
        StringBuilder topMessageBuilder = new StringBuilder("El Test realizado pudo comprobar que tienes más respuestas en las siguientes áreas: ");
        int maxScore = results.values().stream().mapToInt(i -> i).max().orElse(0);
        boolean first = true;
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getValue() == maxScore) {
                if (!first) {
                    topMessageBuilder.append(", ");
                }
                topMessageBuilder.append(entry.getKey());
                first = false;
            }
        }
        topMessageBuilder.append(". Haz click en los áreas para obtener las profesiones recomendadas.");
        String topMessage = topMessageBuilder.toString();
        JTextArea topMessageArea = new JTextArea(topMessage);
        topMessageArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topMessageArea.setForeground(new Color(60, 60, 60));
        topMessageArea.setWrapStyleWord(true);
        topMessageArea.setLineWrap(true);
        topMessageArea.setEditable(false);
        topMessageArea.setBackground(backgroundColor);
        topMessageArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(topMessageArea);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el mensaje destacado y los resultados

        // Ordenar los resultados por puntaje en orden descendente
        List<Map.Entry<String, Integer>> sortedResults = results.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();

        sortedResults.forEach(entry -> {
            JTextArea resultArea = new JTextArea(entry.getKey() + ": " + entry.getValue());
            resultArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
            resultArea.setForeground(new Color(30, 144, 255));
            resultArea.setWrapStyleWord(true);
            resultArea.setLineWrap(true);
            resultArea.setEditable(false);
            resultArea.setBackground(backgroundColor);
            resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            resultArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String areaName = entry.getKey();
                    Area area = findAreaByName(areaName);
                    if (area != null) {
                        showProfessionsByArea(area);
                    }
                }
            });
            panel.add(resultArea);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre cada resultado
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Cerrar");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(70, 130, 180));
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(closeButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Margen
        buttonPanel.setBackground(backgroundColor);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private Area findAreaByName(String areaName) {
        for (Area area : areas) {
            if (area.getNombre().equals(areaName)) {
                return area;
            }
        }
        return null;
    }

    private void showProfessionsByArea(Area area) {
        List<String> professionNames = logic.getProfessionNamesByArea(area);
        Profession.showProfessionsDialog(professionNames, area);
    }

}
