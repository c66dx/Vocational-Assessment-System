package Vista;

import Controlador.TestVocationalLogic;
import Modelo.Area;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class ResultsGUI {
    private final JFrame frame;
    private final Map<String, Integer> results;
    private final List<Area> areas;
    private final TestVocationalLogic logic;

    // Paleta de colores moderna y profesional
    private final Color backgroundColor = Color.decode("#F8F9FA"); // Fondo claro y limpio
    private final Color primaryColor = Color.decode("#007BFF"); // Azul profesional
    private final Color secondaryColor = Color.decode("#6C757D"); // Gris neutro
    private final Color accentColor = Color.decode("#28A745"); // Verde para acciones positivas
    private final Color cardColor = Color.decode("#FFFFFF"); // Blanco para tarjetas

    public ResultsGUI(Map<String, Integer> results, List<Area> areas, TestVocationalLogic logic) {
        this.results = results;
        this.areas = areas;
        this.logic = logic;
        frame = new JFrame("🎯 Resultados del Test Vocacional");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700); // Ventana más grande para mejor visualización
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(backgroundColor);
        frame.setLocationRelativeTo(null);
    }

    public void showResults() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header con título y descripción
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(cardColor);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#DEE2E6"), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        JLabel titleLabel = new JLabel("<html><div style='text-align:center;'>"
                + "<h1 style='color:#007BFF;font-size:32px;margin:0;'>🎯 Resultados del Test Vocacional</h1>"
                + "<p style='color:#6C757D;font-size:16px;margin:10px 0 0 0;'>Descubre tus áreas de mayor afinidad profesional</p>"
                + "</div></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel de contenido con scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(backgroundColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Mensaje informativo
        int maxScore = results.values().stream().mapToInt(i -> i).max().orElse(0);
        StringBuilder topAreasBuilder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (entry.getValue() == maxScore) {
                if (!first) {
                    topAreasBuilder.append(", ");
                }
                topAreasBuilder.append("<strong>").append(entry.getKey().replace("_", " ")).append("</strong>");
                first = false;
            }
        }

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.decode("#E8F4FD"));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        JLabel infoLabel = new JLabel("<html><div style='text-align:center;font-family:Segoe UI;'>"
                + "<p style='color:#007BFF;font-size:18px;margin:0;'>🌟 <strong>Tus áreas de mayor afinidad:</strong></p>"
                + "<p style='color:#212529;font-size:16px;margin:10px 0;'>" + topAreasBuilder.toString() + "</p>"
                + "<p style='color:#6C757D;font-size:14px;margin:0;'><em>💡 Haz clic en cada área para ver las profesiones recomendadas</em></p>"
                + "</div></html>");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        
        contentPanel.add(infoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Crear tarjetas para cada área
        List<Map.Entry<String, Integer>> sortedResults = results.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();

        // Emojis para cada área
        Map<String, String> areaEmojis = Map.of(
            "Arte_Y_Creatividad", "🎨",
            "Ciencias_Sociales", "👥",
            "Economica_Administrativa_Y_Financiera", "💼",
            "Ciencia_Y_Tecnologia", "🔬",
            "Ciencias_Ecologicas_Biologicas_Y_De_La_Salud", "🌱"
        );

        for (int i = 0; i < sortedResults.size(); i++) {
            Map.Entry<String, Integer> entry = sortedResults.get(i);
            JPanel resultCard = createResultCard(entry, i + 1, maxScore, areaEmojis);
            contentPanel.add(resultCard);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(backgroundColor);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton closeButton = createModernButton("❌ Cerrar", secondaryColor);
        closeButton.addActionListener(e -> frame.dispose());
        buttonPanel.add(closeButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /**
     * Crea un botón moderno con estilo profesional
     */
    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    /**
     * Crea una tarjeta moderna para mostrar los resultados de cada área
     */
    private JPanel createResultCard(Map.Entry<String, Integer> entry, int position, int maxScore, Map<String, String> areaEmojis) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(cardColor);
        
        // Color del borde según la posición
        final Color borderColor;
        if (entry.getValue() == maxScore) {
            borderColor = accentColor; // Verde para la puntuación más alta
        } else if (position <= 2) {
            borderColor = primaryColor; // Azul para las primeras posiciones
        } else {
            borderColor = Color.decode("#DEE2E6"); // Gris para el resto
        }
        
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Panel izquierdo con emoji y posición
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(cardColor);
        
        String emoji = areaEmojis.getOrDefault(entry.getKey(), "📊");
        JLabel emojiLabel = new JLabel("<html><div style='text-align:center;'>"
                + "<span style='font-size:32px;'>" + emoji + "</span>"
                + "<br><span style='color:#6C757D;font-size:12px;'>#" + position + "</span>"
                + "</div></html>");
        emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(emojiLabel, BorderLayout.CENTER);

        // Panel central con información del área
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(cardColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        String areaName = entry.getKey().replace("_", " ");
        JLabel nameLabel = new JLabel("<html><div>"
                + "<h3 style='color:#212529;margin:0;font-size:18px;'>" + areaName + "</h3>"
                + "<p style='color:#6C757D;margin:5px 0 0 0;font-size:14px;'>Haz clic para ver profesiones</p>"
                + "</div></html>");
        centerPanel.add(nameLabel, BorderLayout.CENTER);

        // Panel derecho con puntuación
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(cardColor);
        
        // Calcular porcentaje basado en preguntas totales por área
        int questionsInArea = getQuestionsCountByArea(entry.getKey());
        int percentage = questionsInArea > 0 ? (entry.getValue() * 100) / questionsInArea : 0;
        
        JLabel scoreLabel = new JLabel("<html><div style='text-align:center;'>"
                + "<span style='color:#007BFF;font-size:24px;font-weight:bold;'>" + entry.getValue() + "</span>"
                + "<br><span style='color:#28A745;font-size:14px;'>" + percentage + "%</span>"
                + "</div></html>");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(scoreLabel, BorderLayout.CENTER);

        card.add(leftPanel, BorderLayout.WEST);
        card.add(centerPanel, BorderLayout.CENTER);
        card.add(rightPanel, BorderLayout.EAST);

        // Efecto hover
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(Color.decode("#F8F9FA"));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderColor, 3),
                    BorderFactory.createEmptyBorder(19, 24, 19, 24)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(cardColor);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderColor, 2),
                    BorderFactory.createEmptyBorder(20, 25, 20, 25)
                ));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                String areaName = entry.getKey();
                Area area = findAreaByName(areaName);
                if (area != null) {
                    showProfessionsByArea(area);
                }
            }
        });

        return card;
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
        showModernProfessionsDialog(professionNames, area);
    }

    /**
     * Cuenta el número de preguntas por área
     */
    private int getQuestionsCountByArea(String areaName) {
        return (int) logic.getQuestions().stream()
                .filter(question -> question.getArea().equals(areaName))
                .count();
    }

    /**
     * Muestra un diálogo moderno con las profesiones de un área
     */
    private void showModernProfessionsDialog(List<String> professionNames, Area area) {
        JDialog dialog = new JDialog(frame, "Profesiones - " + area.getNombre().replace("_", " "), true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        String emoji = getEmojiForArea(area.getNombre());
        JLabel titleLabel = new JLabel("<html><div style='text-align:center;color:white;'>"
                + "<h2 style='margin:0;font-size:24px;'>" + emoji + " " + area.getNombre().replace("_", " ") + "</h2>"
                + "<p style='margin:5px 0 0 0;font-size:14px;opacity:0.9;'>Profesiones recomendadas en esta área</p>"
                + "</div></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Lista de profesiones
        JPanel professionsPanel = new JPanel();
        professionsPanel.setLayout(new BoxLayout(professionsPanel, BoxLayout.Y_AXIS));
        professionsPanel.setBackground(backgroundColor);
        professionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        if (professionNames != null && !professionNames.isEmpty()) {
            for (int i = 0; i < professionNames.size(); i++) {
                String professionName = professionNames.get(i);
                JPanel professionCard = createProfessionCard(professionName, i + 1);
                professionsPanel.add(professionCard);
                professionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        } else {
            JLabel noDataLabel = new JLabel("<html><div style='text-align:center;color:#6C757D;'>"
                    + "<h3>😔 No hay profesiones disponibles</h3>"
                    + "<p>Esta área no tiene profesiones registradas en la base de datos.</p>"
                    + "</div></html>");
            noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
            professionsPanel.add(noDataLabel);
        }

        JScrollPane scrollPane = new JScrollPane(professionsPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(backgroundColor);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botón cerrar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton closeButton = createModernButton("✅ Entendido", accentColor);
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    /**
     * Crea una tarjeta para cada profesión
     */
    private JPanel createProfessionCard(String professionName, int index) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(cardColor);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#DEE2E6"), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Número de profesión
        JLabel numberLabel = new JLabel(String.valueOf(index));
        numberLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        numberLabel.setForeground(primaryColor);
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setPreferredSize(new Dimension(40, 30));
        numberLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        // Nombre de la profesión
        JLabel nameLabel = new JLabel("<html><div style='margin-left:10px;'>"
                + "<span style='font-size:16px;color:#212529;'>" + professionName.trim() + "</span>"
                + "</div></html>");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        card.add(numberLabel, BorderLayout.WEST);
        card.add(nameLabel, BorderLayout.CENTER);

        // Efecto hover
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(Color.decode("#F8F9FA"));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(primaryColor, 2),
                    BorderFactory.createEmptyBorder(14, 19, 14, 19)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(cardColor);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#DEE2E6"), 1),
                    BorderFactory.createEmptyBorder(15, 20, 15, 20)
                ));
            }
        });

        return card;
    }

    /**
     * Obtiene el emoji correspondiente a cada área
     */
    private String getEmojiForArea(String areaName) {
        return switch (areaName) {
            case "Arte_Y_Creatividad" -> "🎨";
            case "Ciencias_Sociales" -> "👥";
            case "Economica_Administrativa_Y_Financiera" -> "💼";
            case "Ciencia_Y_Tecnologia" -> "🔬";
            case "Ciencias_Ecologicas_Biologicas_Y_De_La_Salud" -> "🌱";
            default -> "📊";
        };
    }

}
