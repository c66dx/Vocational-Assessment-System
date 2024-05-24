package Modelo;

public class Question {
    private int numero;
    private String descripcion;
    private String area;
    private boolean respuesta;

    public Question(int numero, String descripcion, String area) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.area = area;
        this.respuesta = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getArea() {
        return area;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }
}
