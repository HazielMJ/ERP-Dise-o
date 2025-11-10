package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Impuesto")
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_impuesto;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Tipo tipo;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal tasa = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Estado estado = Estado.ACTIVO;

    public enum Tipo { IVA, ISR, IEPS, OTRO }
    public enum Estado { ACTIVO, INACTIVO }

    // === Getters y Setters ===
    public Integer getId_impuesto() { return id_impuesto; }
    public void setId_impuesto(Integer id_impuesto) { this.id_impuesto = id_impuesto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public BigDecimal getTasa() { return tasa; }
    public void setTasa(BigDecimal tasa) { this.tasa = tasa; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}
