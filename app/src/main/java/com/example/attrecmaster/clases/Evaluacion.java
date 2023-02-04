package com.example.attrecmaster.clases;

public class Evaluacion {
    private Integer idevaluacion;
    private String fecha;
    private String tipo;
    private Integer valor;

    public Evaluacion() {
        this.idevaluacion = idevaluacion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Integer getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Integer idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
