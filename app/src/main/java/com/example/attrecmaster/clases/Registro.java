package com.example.attrecmaster.clases;


public class Registro {
    private int id_registro;
    private String asignatura;
    private String grupo;
    private String anio;
    private Integer facultad;

    public Registro() {
        this.id_registro = id_registro;
        this.asignatura = asignatura;
        this.grupo = grupo;
        this.anio = anio;
        this.facultad = facultad;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Integer getFacultad() {
        return facultad;
    }

    public void setFacultad(Integer facultad) {
        this.facultad = facultad;
    }
}

