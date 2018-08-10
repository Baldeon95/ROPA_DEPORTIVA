package com.example.ivonne.ropadeportiva.models;

public class Producto {
    private String nombre;
    private String color;
    private String cantidad;
    private String precio;
    private String sexo;
    private String talla;
    private String tela;

    public Producto() {
    }

    public Producto(String nombre, String color, String cantidad, String precio, String sexo, String talla, String tela) {
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
        this.precio = precio;
        this.sexo = sexo;
        this.talla = talla;
        this.tela = tela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }
}


