package model;

public class Personaje {

    private int id;
    private String nombre;
    private String faccion;
    private String descripcion;

    public Personaje() {}

    public Personaje(int id, String nombre, String faccion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.faccion = faccion;
        this.descripcion = descripcion;
    }

    public Personaje(String nombre, String faccion, String descripcion) {
        this.nombre = nombre;
        this.faccion = faccion;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFaccion() {
        return faccion;
    }
    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
