package com.Vinoteca.AppVinos.dto;

import java.math.BigDecimal;

public class VinoDTO {
    private Long id;
    private String nombre;
    private int anejo;
    private String tipoDeVino;
    private String tipoUva;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private double litro;
    private double alcohol;
    private String imagenUrl;

    private Long idBodega;
    private String nombreBodega;

    public VinoDTO() {}

    public VinoDTO(com.Vinoteca.AppVinos.models.Vinos vino) {
        this.id = vino.getId();
        this.nombre = vino.getNombre();
        this.anejo = vino.getAnejo();
        this.tipoDeVino = vino.getTipoDeVino();
        this.tipoUva = vino.getTipoUva();
        this.descripcion = vino.getDescripcion();
        this.precio = vino.getPrecio();
        this.stock = vino.getStock();
        this.litro = vino.getLitro();
        this.alcohol = vino.getAlcohol();
        this.imagenUrl = vino.getImagenUrl();
        this.id = vino.getId();
        this.nombre = vino.getNombre();

        this.idBodega = vino.getBodega() != null ? vino.getBodega().getId() : null;
        this.nombreBodega = vino.getBodega() != null ? vino.getBodega().getNombre() : null;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnejo() {
        return anejo;
    }

    public void setAnejo(int anejo) {
        this.anejo = anejo;
    }

    public String getTipoDeVino() {
        return tipoDeVino;
    }

    public void setTipoDeVino(String tipoDeVino) {
        this.tipoDeVino = tipoDeVino;
    }

    public String getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(String tipoUva) {
        this.tipoUva = tipoUva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getLitro() {
        return litro;
    }

    public void setLitro(double litro) {
        this.litro = litro;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(Long idBodega) {
        this.idBodega = idBodega;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }
}

