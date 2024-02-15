package com.example.referenciabibliografica;

public class Elemento {
    public String color;
    public String titulo;
    public boolean favorito;

    public Elemento(String color, String titulo, boolean favorito) {
        this.color = color;
        this.titulo = titulo;
        this.favorito = favorito;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

}
