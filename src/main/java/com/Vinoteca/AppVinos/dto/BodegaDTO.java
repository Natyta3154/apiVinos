package com.Vinoteca.AppVinos.dto;

import com.Vinoteca.AppVinos.models.Bodegas;
import jakarta.persistence.Column;

public class BodegaDTO {
    @Column(name = "id_bodegas")
    private Long id;
    @Column(name = "nombre_bodega")
    private String nombre;
    private String pais;
    private String region;
    private String direccion;
    private String descripcion;
    @Column(name = "url_web")
    private String urlWeb;
    private Long stockTotalVinos;
    @Column(name = "imagen_url")
    private String imagenUrl;

    public BodegaDTO(Bodegas bodega) {
        this.id = bodega.getId();
        this.nombre = bodega.getNombre();
        // Asumiendo que la entidad Bodegas tiene estos campos
        this.pais = bodega.getPais();
        this.region = bodega.getRegion();
        this.direccion = bodega.getDireccion();
        this.descripcion = bodega.getDescripcion();
        this.urlWeb = bodega.getUrlWeb();
        this.imagenUrl=bodega.getImagenUrl();

        // Suma el stock de todos los vinos, manejando el caso de lista nula o vacía
        this.stockTotalVinos = bodega.getVinos() != null ?
                bodega.getVinos().stream()
                        .mapToLong(vino -> vino.getStock())
                        .sum() : 0L;
    }

    // Getters y setters para todos los campos

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public Long getStockTotalVinos() {
        return stockTotalVinos;
    }

    public void setStockTotalVinos(Long stockTotalVinos) {
        this.stockTotalVinos = stockTotalVinos;
    }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}
