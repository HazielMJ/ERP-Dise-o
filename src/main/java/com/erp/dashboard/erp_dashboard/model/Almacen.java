package com.erp.dashboard.erp_dashboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "almacen")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Long idAlmacen;

    @Column(name = "codigo_almacen", nullable = false, unique = true, length = 50)
    private String codigoAlmacen;

    @Column(name = "nombre_almacen", nullable = false, length = 100)
    private String nombreAlmacen;

    @Column(name = "ubicacion", length = 150)
    private String ubicacion;

    @Column(name = "capacidad_total")
    private Integer capacidadTotal;

    @Column(name = "espacio_utilizado")
    private Integer espacioUtilizado = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 10)
    private Estado estado = Estado.ACTIVO;

    public enum Estado {
        ACTIVO, INACTIVO
    }

    // Getters y Setters
    public Long getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Long idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public Integer getEspacioUtilizado() {
        return espacioUtilizado;
    }

    public void setEspacioUtilizado(Integer espacioUtilizado) {
        this.espacioUtilizado = espacioUtilizado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
