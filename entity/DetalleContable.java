package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_contable")
public class DetalleContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;

    @Column(nullable = false)
    private Integer id_asiento;

    private Integer numero_linea;

    @Column(length = 50)
    private String cuenta_contable;

    @Column(length = 255)
    private String descripcion_linea;

    @Column(precision = 15, scale = 2)
    private BigDecimal debe;

    @Column(precision = 15, scale = 2)
    private BigDecimal haber;

    // Getters y setters
    public Integer getId_detalle() { return id_detalle; }
    public void setId_detalle(Integer id_detalle) { this.id_detalle = id_detalle; }

    public Integer getId_asiento() { return id_asiento; }
    public void setId_asiento(Integer id_asiento) { this.id_asiento = id_asiento; }

    public Integer getNumero_linea() { return numero_linea; }
    public void setNumero_linea(Integer numero_linea) { this.numero_linea = numero_linea; }

    public String getCuenta_contable() { return cuenta_contable; }
    public void setCuenta_contable(String cuenta_contable) { this.cuenta_contable = cuenta_contable; }

    public String getDescripcion_linea() { return descripcion_linea; }
    public void setDescripcion_linea(String descripcion_linea) { this.descripcion_linea = descripcion_linea; }

    public BigDecimal getDebe() { return debe; }
    public void setDebe(BigDecimal debe) { this.debe = debe; }

    public BigDecimal getHaber() { return haber; }
    public void setHaber(BigDecimal haber) { this.haber = haber; }
}
