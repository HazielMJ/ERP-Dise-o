package com.erp.dashboard.erp_dashboard.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_venta;

    @Column(unique = true, nullable = false)
    private String numero_venta;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha_venta = LocalDateTime.now();

    private Integer id_cliente;
    private Integer id_usuario;

    private Double subtotal = 0.00;
    private Double impuestos = 0.00;
    private Double descuento = 0.00;
    private Double total_venta = 0.00;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    @Enumerated(EnumType.STRING)
    private TipoPago tipo_pago = TipoPago.EFECTIVO;

    private Double monto_recibido = 0.00;
    private Double cambio = 0.00;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // 🔹 Enums
    public enum Estado {
        PENDIENTE, PAGADA, ANULADA
    }

    public enum TipoPago {
        EFECTIVO, TARJETA, TRANSFERENCIA, CREDITO, MIXTO
    }

    // 🔹 Getters & Setters
    public Long getId_venta() {
        return id_venta;
    }
    public void setId_venta(Long id_venta) {
        this.id_venta = id_venta;
    }

    public String getNumero_venta() {
        return numero_venta;
    }
    public void setNumero_venta(String numero_venta) {
        this.numero_venta = numero_venta;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }
    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getImpuestos() {
        return impuestos;
    }
    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getDescuento() {
        return descuento;
    }
    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getTotal_venta() {
        return total_venta;
    }
    public void setTotal_venta(Double total_venta) {
        this.total_venta = total_venta;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoPago getTipo_pago() {
        return tipo_pago;
    }
    public void setTipo_pago(TipoPago tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public Double getMonto_recibido() {
        return monto_recibido;
    }
    public void setMonto_recibido(Double monto_recibido) {
        this.monto_recibido = monto_recibido;
    }

    public Double getCambio() {
        return cambio;
    }
    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
