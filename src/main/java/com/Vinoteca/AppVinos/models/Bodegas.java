package com.Vinoteca.AppVinos.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bodegas") // Buena práctica para definir el nombre de la tabla
public class Bodegas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    private long id;
    @Column(name = "nombre_bodega")
    private String nombre;
    private String pais;
    private String region;
    private String direccion;
    private String descripcion;
    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "url_web")
    private String urlWeb;

    // Relación One-to-Many con Vinos
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY)
    private List<Vinos> vinos;


    public Bodegas() {}

    // Constructor corregido (sin typos)
    public Bodegas( String nombre, String pais, String region, String direccion, String descripcion, String urlWeb, String iamgenUrl) {
        this.nombre = nombre;
        this.pais = pais;
        this.region = region;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.urlWeb = urlWeb;
        this.imagenUrl = imagenUrl;
    }

    // Getter y setter
    public long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() { return pais; } // Getter corregido
    public void setPais(String pais) { this.pais = pais; } // Setter corregido

    public String getRegion() { return region; }
    public void setRegion(String region) {this.region = region; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {this.direccion = direccion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrlWeb() { return urlWeb; }
    public void setUrlWeb(String urlWeb) { this.urlWeb = urlWeb; }

    // Getter y setter para la relación con Bodegas
    public String getImagenUrl(){return imagenUrl;}
    public void setImagenUrl(String imagenUrl){this.imagenUrl=imagenUrl;}

    public List<Vinos> getVinos() { return vinos; }
    public void setVinos (List<Vinos> vinos) { this.vinos = vinos; }


}
