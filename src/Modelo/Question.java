package Modelo;

/**
 * Representa una pregunta del test vocacional
 * 
 * @author Tu Nombre
 * @version 1.0
 * @since 2024
 */
public class Question {
    private final int numero;
    private final String descripcion;
    private final String area;
    private boolean respuesta;
    private boolean respondida;

    /**
     * Constructor para crear una nueva pregunta
     * 
     * @param numero El número de la pregunta
     * @param descripcion La descripción o texto de la pregunta
     * @param area El área vocacional a la que pertenece la pregunta
     */
    public Question(int numero, String descripcion, String area) {
        this.numero = numero;
        this.descripcion = descripcion != null ? descripcion.trim() : "";
        this.area = area != null ? area.trim() : "";
        this.respuesta = false;
        this.respondida = false;
    }

    /**
     * Obtiene el número de la pregunta
     * 
     * @return El número de la pregunta
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene la descripción de la pregunta
     * 
     * @return La descripción de la pregunta
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el área vocacional de la pregunta
     * 
     * @return El nombre del área vocacional
     */
    public String getArea() {
        return area;
    }

    /**
     * Obtiene la respuesta del usuario
     * 
     * @return true si le gusta, false si no le gusta
     */
    public boolean getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta del usuario
     * 
     * @param respuesta true si le gusta, false si no le gusta
     */
    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
        this.respondida = true;
    }

    /**
     * Verifica si la pregunta ha sido respondida
     * 
     * @return true si la pregunta ha sido respondida, false en caso contrario
     */
    public boolean isRespondida() {
        return respondida;
    }

    /**
     * Reinicia la respuesta de la pregunta
     */
    public void reiniciarRespuesta() {
        this.respuesta = false;
        this.respondida = false;
    }

    /**
     * Obtiene el área formateada para mostrar
     * 
     * @return El nombre del área con espacios en lugar de guiones bajos
     */
    public String getAreaFormateada() {
        return area.replace("_", " ");
    }

    @Override
    public String toString() {
        return String.format("Question{numero=%d, area='%s', respondida=%s}", 
                           numero, area, respondida);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Question question = (Question) obj;
        return numero == question.numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}
