package com.erp.erp.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "almacen")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    @JsonProperty("idAlmacen")
    private Long idAlmacen;

    @Column(name = "codigo_almacen", unique = true, nullable = false)
    @JsonProperty("codigoAlmacen") 
    private String codigoAlmacen;

    @Column(name = "nombre_almacen", nullable = false)
    @JsonProperty("nombreAlmacen") 
    private String nombreAlmacen;

    @Column(name = "ubicacion")
    @JsonProperty("ubicacion") 
    private String ubicacion;
    
    @Column(name = "capacidad_total")
    @JsonProperty("capacidadTotal") 
    private BigDecimal capacidadTotal;

    @Column(name = "espacio_utilizado")
    @JsonProperty("espacioUtilizado") 
    private BigDecimal espacioUtilizado;

    @Column(name = "estado", nullable = false)
    @JsonProperty("estado") 
    private String estado;
    
    public Almacen() {}


    
    public Long getIdAlmacen() { return idAlmacen; }
    public void setIdAlmacen(Long idAlmacen) { this.idAlmacen = idAlmacen; }
    public String getCodigoAlmacen() { return codigoAlmacen; }
    public void setCodigoAlmacen(String codigoAlmacen) { this.codigoAlmacen = codigoAlmacen; }
    public String getNombreAlmacen() { return nombreAlmacen; }
    public void setNombreAlmacen(String nombreAlmacen) { this.nombreAlmacen = nombreAlmacen; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public BigDecimal getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(BigDecimal capacidadTotal) { this.capacidadTotal = capacidadTotal; }
    public BigDecimal getEspacioUtilizado() { return espacioUtilizado; }
    public void setEspacioUtilizado(BigDecimal espacioUtilizado) { this.espacioUtilizado = espacioUtilizado; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}