package Controlador;

import Modelo.Question;
import Vista.ResultsGUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TestVocationalGUI {
    private final JFrame frame;
    private final TestVocationalLogic logic;
    private final List<Question> questions;
    private int currentQuestionIndex;

    private final Color backgroundColor = Color.decode("#F5CBA7"); // Color de los botones
    private final Color buttonColor = Color.decode("#EC33FF"); // Color de fondo del JFrame

    public TestVocationalGUI() {
        frame = new JFrame("Test Vocacional");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(backgroundColor);

        logic = new TestVocationalLogic();
        questions = logic.getQuestions();
        currentQuestionIndex = 0;

        showInstructions();
    }

    private void showInstructions() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);

        JTextPane instructions = new JTextPane();
        instructions.setContentType("text/html");
        instructions.setText("<html><body style='font-family:Serif;font-size:14px;text-align:justify;'>"
                + "<h1 style='text-align:center;'>Bienvenido al Test Vocacional</h1>"
                + "<p><strong>Instrucciones:</strong></p>"
                + "<ol>"
                + "<li>Lee atentamente cada una de las actividades.</li>"
                + "<li>Marca la casilla a gusto según la actividad mencionada. Solo puedes escoger una respuesta.</li>"
                + "<li>Si no marcas ninguna respuesta el programa automáticamente lo hará por ti.</li>"
                + "<li>Haz clic en 'Siguiente' para continuar con la siguiente actividad.</li>"
                + "</ol>"
                + "</body></html>");
        instructions.setEditable(false);
        instructions.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        instructions.setBackground(backgroundColor);
        panel.add(instructions, BorderLayout.CENTER);

        JButton nextButton = new JButton("Siguiente");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(buttonColor);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> showQuestion());
        panel.add(nextButton, BorderLayout.SOUTH);

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showQuestion() {
        // Verifica si hay más preguntas por mostrar
        if (currentQuestionIndex < questions.size() - 1) {
            // Muestra la pregunta actual
            mostrarPregunta(currentQuestionIndex);
        } else {
            // Si estamos en la última pregunta, muestra la pregunta y agrega el botón de resultados
            mostrarPregunta(currentQuestionIndex);
            JButton resultsButton = new JButton("Resultados");
            resultsButton.setFont(new Font("Arial", Font.BOLD, 14));
            resultsButton.setBackground(buttonColor);
            resultsButton.setForeground(Color.WHITE);
            resultsButton.addActionListener(e -> calculateAndShowResults());

            JButton closeButton = new JButton("Cerrar");
            closeButton.setFont(new Font("Arial", Font.BOLD, 14));
            closeButton.setBackground(buttonColor);
            closeButton.setForeground(Color.WHITE);
            closeButton.addActionListener(e -> frame.dispose());

            JButton restartButton = new JButton("Reiniciar");
            restartButton.setFont(new Font("Arial", Font.BOLD, 14));
            restartButton.setBackground(buttonColor);
            restartButton.setForeground(Color.WHITE);
            restartButton.addActionListener(e -> {
                currentQuestionIndex = 0;
                showInstructions();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBackground(backgroundColor);
            buttonPanel.add(restartButton);
            buttonPanel.add(closeButton);
            buttonPanel.add(resultsButton);

            frame.getContentPane().remove(buttonPanel); // Elimina el panel existente
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Agrega los botones
            frame.revalidate();
            frame.repaint();

            return; // Salir de la función para evitar que se muestre el botón "Siguiente"
        }

        // Muestra los botones "Anterior" y "Siguiente" solo si no estamos en la última pregunta
        JButton nextButton = new JButton("Siguiente");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(buttonColor);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(e -> showNextQuestion());

        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setBackground(backgroundColor);

        JButton prevButton = new JButton("Anterior");
        prevButton.setFont(new Font("Arial", Font.BOLD, 14));
        prevButton.setBackground(buttonColor);
        prevButton.setForeground(Color.WHITE);
        prevButton.addActionListener(e -> showPreviousQuestion());

        navPanel.add(prevButton);
        navPanel.add(nextButton);

        frame.getContentPane().remove(navPanel); // Elimina el panel existente
        frame.getContentPane().add(navPanel, BorderLayout.SOUTH); // Agrega los botones "Anterior" y "Siguiente"
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarPregunta(int index) {
        currentQuestionIndex = index;
        Question currentQuestion = questions.get(currentQuestionIndex);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(backgroundColor);

        JLabel questionLabel = new JLabel("<html><body style='font-family:Serif;font-size:16px;text-align:justify;'>"
                + currentQuestion.getNumero() + ". " + currentQuestion.getDescripcion()
                + "</body></html>");
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        // Botones para "Me gusta" y "No me gusta"
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton meGustaButton = new JRadioButton("Me gusta");
        JRadioButton noMeGustaButton = new JRadioButton("No me gusta");

        meGustaButton.setFont(new Font("Arial", Font.PLAIN, 14));
        noMeGustaButton.setFont(new Font("Arial", Font.PLAIN, 14));

        meGustaButton.setSelected(currentQuestion.getRespuesta());
        noMeGustaButton.setSelected(!currentQuestion.getRespuesta());

        // Establecer el color de fondo de los botones
        meGustaButton.setBackground(Color.decode("#F5CBA7"));
        noMeGustaButton.setBackground(Color.decode("#F5CBA7"));

        meGustaButton.addActionListener(e -> {
            currentQuestion.setRespuesta(true);
            noMeGustaButton.setSelected(false);
        });
        noMeGustaButton.addActionListener(e -> {
            currentQuestion.setRespuesta(false);
            meGustaButton.setSelected(false);
        });

        buttonGroup.add(meGustaButton);
        buttonGroup.add(noMeGustaButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(meGustaButton);
        buttonPanel.add(noMeGustaButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }


    private void showNextQuestion() {
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
        Map<String, Integer> results = logic.calculateResults(questions);
        ResultsGUI resultsGUI = new ResultsGUI(results, logic.getAreas(), logic);
        resultsGUI.showResults();
    }

    public void startTest() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        TestVocationalGUI testVocationalGUI = new TestVocationalGUI();
        testVocationalGUI.startTest();
    }
}