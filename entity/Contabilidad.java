package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Contabilidad")
public class Contabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asiento;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_asiento;

    @Column(unique = true)
    private String numero_asiento;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.BORRADOR;

    private Integer id_usuario;

    private BigDecimal total_debe;
    private BigDecimal total_haber;

    private String periodo_contable;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha_contabilizacion;

    public enum Estado { BORRADOR, CONTABILIZADO, ANULADO }

    // Getters y Setters
    public Integer getId_asiento() { return id_asiento; }
    public void setId_asiento(Integer id_asiento) { this.id_asiento = id_asiento; }

    public Date getFecha_asiento() { return fecha_asiento; }
    public void setFecha_asiento(Date fecha_asiento) { this.fecha_asiento = fecha_asiento; }

    public String getNumero_asiento() { return numero_asiento; }
    public void setNumero_asiento(String numero_asiento) { this.numero_asiento = numero_asiento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Integer getId_usuario() { return id_usuario; }
    public void setId_usuario(Integer id_usuario) { this.id_usuario = id_usuario; }

    public BigDecimal getTotal_debe() { return total_debe; }
    public void setTotal_debe(BigDecimal total_debe) { this.total_debe = total_debe; }

    public BigDecimal getTotal_haber() { return total_haber; }
    public void setTotal_haber(BigDecimal total_haber) { this.total_haber = total_haber; }

    public String getPeriodo_contable() { return periodo_contable; }
    public void setPeriodo_contable(String periodo_contable) { this.periodo_contable = periodo_contable; }

    public Date getFecha_contabilizacion() { return fecha_contabilizacion; }
    public void setFecha_contabilizacion(Date fecha_contabilizacion) { this.fecha_contabilizacion = fecha_contabilizacion; }



    @Override
    public String toString() {
        return "Contabilidad{" +
                "id_asiento=" + id_asiento +
                ", fecha_asiento=" + fecha_asiento +
                ", numero_asiento='" + numero_asiento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", id_usuario=" + id_usuario +
                ", total_debe=" + total_debe +
                ", total_haber=" + total_haber +
                ", periodo_contable='" + periodo_contable + '\'' +
                ", fecha_contabilizacion=" + fecha_contabilizacion +
                '}';
    }
}
