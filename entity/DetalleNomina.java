package com.erp.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_nomina")
public class DetalleNomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_nomina;

    private Integer id_nomina;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String concepto;
    private String clave_sat;
    private Double monto;

    public enum Tipo {
        PERCEPCION,
        DEDUCCION
    }

    // Getters y Setters
    public Integer getId_detalle_nomina() { return id_detalle_nomina; }
    public void setId_detalle_nomina(Integer id_detalle_nomina) { this.id_detalle_nomina = id_detalle_nomina; }

    public Integer getId_nomina() { return id_nomina; }
    public void setId_nomina(Integer id_nomina) { this.id_nomina = id_nomina; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public String getClave_sat() { return clave_sat; }
    public void setClave_sat(String clave_sat) { this.clave_sat = clave_sat; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
}
