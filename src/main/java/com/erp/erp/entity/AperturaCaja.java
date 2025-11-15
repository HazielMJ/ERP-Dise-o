package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "apertura_caja")
public class AperturaCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apertura")
    private Long idApertura;

    @Column(name = "id_caja", nullable = false)
    private Long idCaja;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Column(name = "total_ingresos")
    private BigDecimal totalIngresos;

    @Column(name = "total_egresos")
    private BigDecimal totalEgresos;

    @Column(name = "saldo_esperado", insertable = false, updatable = false)
    private BigDecimal saldoEsperado;

    @Column(name = "saldo_real")
    private BigDecimal saldoReal;

    @Column(name = "diferencia", insertable = false, updatable = false)
    private BigDecimal diferencia;

    @Column(name = "estado")
    private String estado;

    public Long getIdApertura() { return idApertura; }
    public void setIdApertura(Long idApertura) { this.idApertura = idApertura; }
    public Long getIdCaja() { return idCaja; }
    public void setIdCaja(Long idCaja) { this.idCaja = idCaja; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public BigDecimal getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(BigDecimal saldoInicial) { this.saldoInicial = saldoInicial; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public LocalDateTime getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDateTime fechaApertura) { this.fechaApertura = fechaApertura; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
}