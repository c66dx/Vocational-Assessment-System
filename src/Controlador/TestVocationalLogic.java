package Controlador;

import Modelo.Area;
import Modelo.Profession;
import Modelo.Question;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestVocationalLogic {
    private final List<Area> areas;
    private final List<Question> questions;

    public TestVocationalLogic() {
        areas = new ArrayList<>();
        questions = new ArrayList<>();
        loadQuestionsFromFile();
    }

    private void loadQuestionsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/questions.txt"))) {
            int numAreas = Integer.parseInt(br.readLine());
            for (int i = 0; i < numAreas; i++) {
                String[] areaLine = br.readLine().split(" ", 2);
                String areaName = areaLine[0];
                String[] professionNames = areaLine[1].split(";");
                List<Profession> professions = new ArrayList<>();
                for (String professionName : professionNames) {
                    professions.add(new Profession(professionName.trim()));
                }
                areas.add(new Area(areaName, professions));
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                int questionNumber = Integer.parseInt(parts[0]);

                int lastSpaceIndex = parts[1].lastIndexOf(' ');
                String description = parts[1].substring(0, lastSpaceIndex).trim();
                String areaName = parts[1].substring(lastSpaceIndex + 1).trim();

                questions.add(new Question(questionNumber, description, areaName));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de preguntas: " + e.getMessage());
            // En una aplicación real, se podría usar un logger apropiado
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public Map<String, Integer> calculateResults(List<Question> answeredQuestions) {
        Map<String, Integer> results = new HashMap<>();
        for (Question question : answeredQuestions) {
            String area = question.getArea();
            int score = question.getRespuesta() ? 1 : 0;
            results.put(area, results.getOrDefault(area, 0) + score);
        }
        return results;
    }

    public List<String> getProfessionNamesByArea(Area area) {
        for (Area a : areas) {
            if (a.getNombre().equals(area.getNombre())) {
                List<String> professionNames = new ArrayList<>();
                for (Profession profession : a.getProfesiones()) {
                    professionNames.add(profession.getNombre());
                }
                return professionNames;
            }
        }
        return Collections.emptyList();
    }
}
