package com.Vinoteca.AppVinos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "vinos")
public class Vinos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vino")
    private Long id;

    @Column(name = "nombre_vino")
    private String nombre;

    @Column(name = "añada")
    private int anejo;
    @Column(name = "tipo_vino")
    private String tipoDeVino;
    @Column(name = "variedad_uva")
    private String tipoUva;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    @Column(name = "volumen")
    private double litro;
    private double alcohol;

    @Column(name = "imagen_url")
    private String imagenUrl;

    // Relación ManyToMany inversa: qué ofertas incluyen este vino
    @ManyToMany(mappedBy = "vinos")
    @JsonIgnoreProperties("vinos")
    private Set<Oferta> ofertas = new HashSet<>();

    public Set<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(Set<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    // Relación Muchos a Uno, corregida a nombre singular 'bodega'
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bodega", nullable = true)
    @JsonIgnore // << rompe ciclo, no serializa Bodega dentro de Vino
    private Bodegas bodega; // Nombre de la variable en singular


    public Vinos() {}

    // Constructor corregido para evitar el typo en 'descripcion'
    public Vinos (Long id,String nombre, int anejo, String tipoDeVino, String tipoUva, String descripcion, BigDecimal precio, int stock, double litro, double alcohol, String imagenUrl){
        this.nombre = nombre;
        this.anejo = anejo;
        this.tipoDeVino = tipoDeVino;
        this.tipoUva = tipoUva;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.litro = litro;
        this.alcohol = alcohol;
        this.imagenUrl = imagenUrl;

    }

    // Getter y setter

    public Long getId(){ return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public int getAnejo(){return anejo;}
    public void setAnejo(int anejo){this.anejo = anejo;}

    public String getTipoDeVino(){return tipoDeVino;}
    public void setTipoDeVino(String tipoDeVino){this.tipoDeVino = tipoDeVino;}

    public String getTipoUva(){return tipoUva;}
    public void setTipoUva(String tipoUva){this.tipoUva = tipoUva;}

    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}

    public BigDecimal getPrecio(){return precio;}
    public void setPrecio(BigDecimal precio){this.precio = precio;}

    public int getStock(){return stock;}
    public void setStock(int stock){this.stock = stock;}

    public double getLitro(){return litro;}
    public void setLitro(double litro){this.litro = litro;}

    public double getAlcohol(){return alcohol;}
    public void setAlcohol (double alcohol){this.alcohol = alcohol;}

    public String getImagenUrl(){return imagenUrl;}
    public void setImagenUrl(String imagenUrl){this.imagenUrl = imagenUrl;}



    public Bodegas getBodega() {
        return bodega;
    }
    public void setBodega(Bodegas bodega) {
        this.bodega = bodega;
    }

}
