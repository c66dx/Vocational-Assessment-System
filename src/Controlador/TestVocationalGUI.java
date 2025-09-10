package Controlador;

import Modelo.Question;
import Vista.ResultsGUI;
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class TestVocationalGUI {
    private final JFrame frame;
    private final TestVocationalLogic logic;
    private final List<Question> questions;
    private int currentQuestionIndex;

    // Paleta de colores simplificada - solo los que se usan
    private final Color backgroundColor = Color.decode("#1e293b"); // Fondo gris azulado
    private final Color primaryColor = Color.decode("#3b82f6"); // Azul vibrante
    private final Color secondaryColor = Color.decode("#8b5cf6"); // Púrpura
    private final Color accentColor = Color.decode("#10b981"); // Verde esmeralda (para stats)
    private final Color textColor = Color.decode("#f8fafc"); // Texto muy claro
    private final Color cardColor = Color.decode("#334155"); // Tarjetas gris azulado

    public TestVocationalGUI() {
        frame = new JFrame("🎯 Test Vocacional - Orientación Profesional");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700); // Ventana más grande y responsiva
        frame.setMinimumSize(new Dimension(800, 600)); // Tamaño mínimo
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(backgroundColor);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa por defecto

        logic = new TestVocationalLogic();
        questions = logic.getQuestions();
        currentQuestionIndex = 0;

        showInstructions();
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


    private void showInstructions() {
        // Panel principal simple
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Panel de contenido
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(cardColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Título principal
        JLabel titleLabel = new JLabel("<html><div style='text-align:center;'>"
                + "<h1 style='color:#4f46e5;font-size:32px;margin:0;'>🎯 TEST VOCACIONAL</h1>"
                + "<p style='color:#94a3b8;font-size:18px;margin:15px 0;'>Descubre tu orientación profesional</p>"
                + "</div></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de instrucciones
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.setBackground(cardColor);
        instructionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));

        // Instrucciones simples
        String[] instructions = {
            "1. Lee atentamente cada actividad presentada",
            "2. Selecciona 'Me gusta' o 'No me gusta' según tu preferencia",
            "3. Navega entre preguntas usando los botones de navegación",
            "4. Al finalizar, obtén tus resultados personalizados"
        };

        for (String instruction : instructions) {
            JLabel instructionLabel = new JLabel(instruction);
            instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            instructionLabel.setForeground(textColor);
            instructionLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            instructionsPanel.add(instructionLabel);
        }

        contentPanel.add(instructionsPanel, BorderLayout.CENTER);

        // Panel de estadísticas
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        statsPanel.setBackground(cardColor);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        statsPanel.add(createSimpleStatCard("20", "Preguntas"));
        statsPanel.add(createSimpleStatCard("5", "Áreas"));
        statsPanel.add(createSimpleStatCard("100+", "Profesiones"));

        contentPanel.add(statsPanel, BorderLayout.SOUTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Panel para el botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JButton nextButton = createModernButton("Comenzar Test", primaryColor);
        nextButton.addActionListener(e -> showQuestion());
        buttonPanel.add(nextButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
    }





    /**
     * Crea tarjetas de estadísticas simples
     */
    private JPanel createSimpleStatCard(String number, String label) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 20));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 60), 1),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        
        JLabel content = new JLabel("<html><div style='text-align:center;'>"
                + "<div style='color:#f1f5f9;font-size:24px;font-weight:600;'>" + number + "</div>"
                + "<div style='color:#94a3b8;font-size:14px;'>" + label + "</div>"
                + "</div></html>");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(content, BorderLayout.CENTER);
        
        return card;
    }

    private void showQuestion() {
        // Limpiar completamente la ventana para evitar superposiciones
        frame.getContentPane().removeAll();
        
        // Mostrar la pregunta actual
        mostrarPregunta(currentQuestionIndex);
        
        // Crear panel de navegación limpio
        createNavigationPanel();
        
        frame.revalidate();
        frame.repaint();
    }
    
    private void createNavigationPanel() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        navPanel.setBackground(backgroundColor);
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Botón anterior (solo si no es la primera pregunta)
        if (currentQuestionIndex > 0) {
            JButton prevButton = createModernButton("⬅️ Anterior", secondaryColor);
            prevButton.addActionListener(e -> showPreviousQuestion());
            navPanel.add(prevButton);
        }

        // Botón siguiente o finalizar
        if (currentQuestionIndex < questions.size() - 1) {
            JButton nextButton = createModernButton("➡️ Siguiente", primaryColor);
            nextButton.addActionListener(e -> showNextQuestion());
            navPanel.add(nextButton);
        } else {
            // Última pregunta - botones de finalización
            JButton resultsButton = createModernButton("📊 Ver Resultados", accentColor);
            resultsButton.addActionListener(e -> calculateAndShowResults());
            navPanel.add(resultsButton);
            
            JButton restartButton = createModernButton("🔄 Reiniciar", primaryColor);
            restartButton.addActionListener(e -> {
                resetAllAnswers();
                showInstructions();
            });
            navPanel.add(restartButton);
        }

        frame.add(navPanel, BorderLayout.SOUTH);
    }

    public void mostrarPregunta(int index) {
        currentQuestionIndex = index;
        Question currentQuestion = questions.get(currentQuestionIndex);

        // Panel principal con diseño mejorado
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header moderno con progreso
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(cardColor);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 50), 1),
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        
        // Título de la pregunta más elegante
        JLabel titleLabel = new JLabel("🎯 Test Vocacional - Orientación Profesional");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(primaryColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Información de progreso mejorada
        JPanel progressInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        progressInfoPanel.setBackground(cardColor);
        
        JLabel progressLabel = new JLabel("Pregunta " + currentQuestion.getNumero() + " de " + questions.size());
        progressLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressLabel.setForeground(textColor);
        
        JLabel areaLabel = new JLabel("📋 Área: " + currentQuestion.getAreaFormateada());
        areaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        areaLabel.setForeground(secondaryColor);
        
        progressInfoPanel.add(progressLabel);
        progressInfoPanel.add(new JLabel(" | "));
        progressInfoPanel.add(areaLabel);
        headerPanel.add(progressInfoPanel, BorderLayout.CENTER);
        
        // Barra de progreso más atractiva
        JProgressBar progressBar = new JProgressBar(0, questions.size());
        progressBar.setValue(currentQuestion.getNumero());
        progressBar.setStringPainted(true);
        progressBar.setString(Math.round((float)currentQuestion.getNumero() / questions.size() * 100) + "% completado");
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 11));
        progressBar.setForeground(accentColor);
        progressBar.setBackground(new Color(cardColor.getRed(), cardColor.getGreen(), cardColor.getBlue(), 100));
        progressBar.setBorderPainted(true);
        progressBar.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1));
        progressBar.setPreferredSize(new Dimension(0, 30));
        
        JPanel progressContainer = new JPanel(new BorderLayout());
        progressContainer.setBackground(cardColor);
        progressContainer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        progressContainer.add(progressBar, BorderLayout.CENTER);
        headerPanel.add(progressContainer, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Panel de contenido
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(cardColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Panel de la pregunta con diseño mejorado
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBackground(backgroundColor);
        questionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        // Pregunta con mejor formato
        JLabel questionLabel = new JLabel("<html><div style='text-align:center; line-height:1.4;'>" +
            "<div style='color:#3b82f6; font-size:16px; margin-bottom:15px;'>💭 " + 
            currentQuestion.getAreaFormateada() + "</div>" +
            "<div style='color:#f8fafc; font-size:18px; font-weight:600;'>" + 
            currentQuestion.getDescripcion() + "</div></html>");
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        
        contentPanel.add(questionPanel, BorderLayout.CENTER);

        // Panel de respuestas mejorado
        JPanel answersPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        answersPanel.setBackground(cardColor);
        answersPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        answersPanel.setPreferredSize(new Dimension(0, 140));

        // Botones de respuesta SIMPLES
        JButton meGustaButton = new JButton("Me gusta");
        JButton noMeGustaButton = new JButton("No me gusta");
        
        // Configuración BÁSICA que funciona
        meGustaButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        meGustaButton.setPreferredSize(new Dimension(250, 70));
        meGustaButton.setFocusPainted(false);
        meGustaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        noMeGustaButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        noMeGustaButton.setPreferredSize(new Dimension(250, 70));
        noMeGustaButton.setFocusPainted(false);
        noMeGustaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Estado inicial
        updateSimpleButtonState(meGustaButton, noMeGustaButton, currentQuestion);

        meGustaButton.addActionListener(e -> {
            currentQuestion.setRespuesta(true);
            updateSimpleButtonState(meGustaButton, noMeGustaButton, currentQuestion);
        });
        
        noMeGustaButton.addActionListener(e -> {
            currentQuestion.setRespuesta(false);
            updateSimpleButtonState(meGustaButton, noMeGustaButton, currentQuestion);
        });

        answersPanel.add(meGustaButton);
        answersPanel.add(noMeGustaButton);
        contentPanel.add(answersPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Actualiza el estado visual SIMPLE Y DIRECTO
     */
    private void updateSimpleButtonState(JButton meGustaButton, JButton noMeGustaButton, Question question) {
        if (question.isRespondida() && question.getRespuesta()) {
            // "Me gusta" seleccionado
            meGustaButton.setBackground(Color.GREEN);
            meGustaButton.setForeground(Color.WHITE);
            meGustaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            
            noMeGustaButton.setBackground(Color.LIGHT_GRAY);
            noMeGustaButton.setForeground(Color.BLACK);
            noMeGustaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
        } else if (question.isRespondida() && !question.getRespuesta()) {
            // "No me gusta" seleccionado
            meGustaButton.setBackground(Color.LIGHT_GRAY);
            meGustaButton.setForeground(Color.BLACK);
            meGustaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
            noMeGustaButton.setBackground(Color.RED);
            noMeGustaButton.setForeground(Color.WHITE);
            noMeGustaButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            
        } else {
            // Sin respuesta
            meGustaButton.setBackground(Color.LIGHT_GRAY);
            meGustaButton.setForeground(Color.BLACK);
            meGustaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
            noMeGustaButton.setBackground(Color.LIGHT_GRAY);
            noMeGustaButton.setForeground(Color.BLACK);
            noMeGustaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
    }




    private void showNextQuestion() {
        // Validar que la pregunta actual esté respondida
        if (!questions.get(currentQuestionIndex).isRespondida()) {
            showValidationMessage("⚠️ Por favor responde la pregunta actual antes de continuar.");
            return;
        }
        
        currentQuestionIndex++;
        showQuestion(); // Se asegura de mostrar la próxima pregunta
    }

    private void showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            showQuestion();
        }
    }

    private void calculateAndShowResults() {
        // Validar que todas las preguntas estén respondidas
        long unansweredQuestions = questions.stream()
                .filter(q -> !q.isRespondida())
                .count();
        
        if (unansweredQuestions > 0) {
            showValidationMessage("""
                    ⚠️ Debes responder todas las preguntas antes de ver los resultados.
                    Preguntas sin responder: """ + unansweredQuestions);
            return;
        }
        
        Map<String, Integer> results = logic.calculateResults(questions);
        ResultsGUI resultsGUI = new ResultsGUI(results, logic.getAreas(), logic);
        resultsGUI.showResults();
    }

    /**
     * Muestra un mensaje de validación al usuario
     */
    private void showValidationMessage(String message) {
        JOptionPane.showMessageDialog(
            frame,
            message,
            "Validación",
            JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Reinicia todas las respuestas del test
     */
    private void resetAllAnswers() {
        questions.forEach(Question::reiniciarRespuesta);
        currentQuestionIndex = 0;
    }

    public void startTest() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        TestVocationalGUI testVocationalGUI = new TestVocationalGUI();
        testVocationalGUI.startTest();
    }
}