package com.example.referenciabibliografica;

public class Palabra {
    private String palabra;
    private String significado;

    public Palabra() {
    }

    public Palabra(String palabra, String significado) {
        this.palabra = palabra;
        this.significado = significado;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }
}
