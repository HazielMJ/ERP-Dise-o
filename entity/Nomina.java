package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Nomina")
public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_nomina;

    @Column(nullable = false)
    private Integer id_empleado;

    @Column(nullable = true)
    private Integer id_asiento_contable;

    @Column(length = 30, nullable = false)
    private String tipo_nomina; // Ej: QUINCENAL, SEMANAL, MENSUAL

    @Column(length = 20, nullable = false)
    private String periodo_nomina; // Ej: "2025-10"
@Temporal(TemporalType.DATE)
@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
private Date fecha_inicio;

@Temporal(TemporalType.DATE)
@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
private Date fecha_fin;

@Temporal(TemporalType.DATE)
@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
private Date fecha_pago;


    @Column(nullable = false)
    private Integer dias_trabajados;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal salario_diario = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal total_percepciones = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal total_deducciones = BigDecimal.ZERO;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal total_neto = BigDecimal.ZERO;

    @Column(length = 20, nullable = false)
    private String estado = "ACTIVA"; // ACTIVA, CERRADA, CANCELADA

    // ===========================
    // GETTERS Y SETTERS
    // ===========================

    public Integer getId_nomina() {
        return id_nomina;
    }

    public void setId_nomina(Integer id_nomina) {
        this.id_nomina = id_nomina;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Integer id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Integer getId_asiento_contable() {
        return id_asiento_contable;
    }

    public void setId_asiento_contable(Integer id_asiento_contable) {
        this.id_asiento_contable = id_asiento_contable;
    }

    public String getTipo_nomina() {
        return tipo_nomina;
    }

    public void setTipo_nomina(String tipo_nomina) {
        this.tipo_nomina = tipo_nomina;
    }

    public String getPeriodo_nomina() {
        return periodo_nomina;
    }

    public void setPeriodo_nomina(String periodo_nomina) {
        this.periodo_nomina = periodo_nomina;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Integer getDias_trabajados() {
        return dias_trabajados;
    }

    public void setDias_trabajados(Integer dias_trabajados) {
        this.dias_trabajados = dias_trabajados;
    }

    public BigDecimal getSalario_diario() {
        return salario_diario;
    }

    public void setSalario_diario(BigDecimal salario_diario) {
        this.salario_diario = salario_diario;
    }

    public BigDecimal getTotal_percepciones() {
        return total_percepciones;
    }

    public void setTotal_percepciones(BigDecimal total_percepciones) {
        this.total_percepciones = total_percepciones;
    }

    public BigDecimal getTotal_deducciones() {
        return total_deducciones;
    }

    public void setTotal_deducciones(BigDecimal total_deducciones) {
        this.total_deducciones = total_deducciones;
    }

    public BigDecimal getTotal_neto() {
        return total_neto;
    }

    public void setTotal_neto(BigDecimal total_neto) {
        this.total_neto = total_neto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ===========================
    // MÉTODO toString
    // ===========================
    @Override
    public String toString() {
        return "Nomina{" +
                "id_nomina=" + id_nomina +
                ", id_empleado=" + id_empleado +
                ", id_asiento_contable=" + id_asiento_contable +
                ", tipo_nomina='" + tipo_nomina + '\'' +
                ", periodo_nomina='" + periodo_nomina + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", fecha_pago=" + fecha_pago +
                ", dias_trabajados=" + dias_trabajados +
                ", salario_diario=" + salario_diario +
                ", total_percepciones=" + total_percepciones +
                ", total_deducciones=" + total_deducciones +
                ", total_neto=" + total_neto +
                ", estado='" + estado + '\'' +
                '}';
    }
}
