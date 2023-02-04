package com.example.attrecmaster.clases;

public class Estudiante {
    private int idestudiante;
    private String nombre;
    private Integer ci;
    private String sexo;

    public Estudiante() {
        this.idestudiante = idestudiante;
        this.nombre = nombre;
        this.ci = ci;
        this.sexo = sexo;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
