package com.erp.erp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "venta",
    indexes = {
        @Index(name = "idx_fecha", columnList = "fecha_venta"),
        @Index(name = "idx_cliente", columnList = "id_cliente"),
        @Index(name = "idx_estado", columnList = "estado"),
        @Index(name = "idx_numero", columnList = "numero_venta")
    }
)
public class Venta {

    public enum Estado { PENDIENTE, PAGADA, ANULADA }
    public enum TipoPago { EFECTIVO, TARJETA, TRANSFERENCIA, CREDITO, MIXTO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    @JsonProperty("id_venta") 
    private Long id;

 @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "numero_venta", unique = true, nullable = false, length = 50)
    private String numeroVenta;

    @CreationTimestamp
    @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fechaVenta;

    @NotNull
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    // Montos
    @PositiveOrZero
    @Column(name = "subtotal", precision = 15, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "impuestos", precision = 15, scale = 2)
    private BigDecimal impuestos = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "descuento", precision = 15, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "total_venta", precision = 15, scale = 2)
    private BigDecimal totalVenta = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private Estado estado = Estado.PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pago", length = 20)
    private TipoPago tipoPago = TipoPago.EFECTIVO;

    @PositiveOrZero
    @Column(name = "monto_recibido", precision = 15, scale = 2)
    private BigDecimal montoRecibido = BigDecimal.ZERO;

    @PositiveOrZero
    @Column(name = "cambio", precision = 15, scale = 2)
    private BigDecimal cambio = BigDecimal.ZERO;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroVenta() { return numeroVenta; }
    public void setNumeroVenta(String numeroVenta) { this.numeroVenta = numeroVenta; }

    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta) { this.fechaVenta = fechaVenta; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getImpuestos() { return impuestos; }
    public void setImpuestos(BigDecimal impuestos) { this.impuestos = impuestos; }

    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }

    public BigDecimal getTotalVenta() { return totalVenta; }
    public void setTotalVenta(BigDecimal totalVenta) { this.totalVenta = totalVenta; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public TipoPago getTipoPago() { return tipoPago; }
    public void setTipoPago(TipoPago tipoPago) { this.tipoPago = tipoPago; }

    public BigDecimal getMontoRecibido() { return montoRecibido; }
    public void setMontoRecibido(BigDecimal montoRecibido) { this.montoRecibido = montoRecibido; }

    public BigDecimal getCambio() { return cambio; }
    public void setCambio(BigDecimal cambio) { this.cambio = cambio; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @PrePersist
    @PreUpdate
    private void recalcularYValidar() {
        BigDecimal sub = subtotal != null ? subtotal : BigDecimal.ZERO;
        BigDecimal imp = impuestos != null ? impuestos : BigDecimal.ZERO;
        BigDecimal desc = descuento != null ? descuento : BigDecimal.ZERO;

        BigDecimal total = sub.add(imp).subtract(desc);
        if (total.signum() < 0) total = BigDecimal.ZERO;
        this.totalVenta = total;

        if (estado == Estado.PAGADA) {
            BigDecimal rec = montoRecibido != null ? montoRecibido : BigDecimal.ZERO;
            if (rec.compareTo(this.totalVenta) < 0) {
                throw new IllegalArgumentException("monto_recibido < total_venta");
            }
            BigDecimal diff = rec.subtract(this.totalVenta);
            this.cambio = diff.signum() > 0 ? diff : BigDecimal.ZERO;
        } else {
            this.cambio = BigDecimal.ZERO;
        }
    }
}