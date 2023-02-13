package com.example.attrecmaster.clases;

public class EstudiantesEvaluados {
    private int idestudiante;
    private String nombre;
    private String ci;
    private String sexo;
    private int valor;

    public EstudiantesEvaluados() {
        this.idestudiante = idestudiante;
        this.nombre = nombre;
        this.ci = ci;
        this.sexo = sexo;
        this.valor = valor;
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

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
