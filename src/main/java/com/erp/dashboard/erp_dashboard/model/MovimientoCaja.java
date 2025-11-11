package com.erp.dashboard.erp_dashboard.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_caja")
public class MovimientoCaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;

    // ✅ Relación con apertura_caja
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apertura", nullable = false)
    private AperturaCaja aperturaCaja;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo_movimiento;

    @Enumerated(EnumType.STRING)
    private CategoriaMovimiento categoria;

    private double monto;
    private String concepto;
    private int id_usuario;
    private Long id_venta;

    @Enumerated(EnumType.STRING)
    private FormaPago forma_pago = FormaPago.EFECTIVO;

    private String referencia;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private LocalDateTime fecha_movimiento = LocalDateTime.now();

    // ENUMS
    public enum TipoMovimiento { INGRESO, EGRESO }
    public enum CategoriaMovimiento { VENTA, COMPRA, GASTO, RETIRO, DEPOSITO, AJUSTE, OTRO }
    public enum FormaPago { EFECTIVO, TARJETA, TRANSFERENCIA, MIXTO }

    // CONSTRUCTORES
    public MovimientoCaja() {}

    // GETTERS Y SETTERS
    public Long getId_movimiento() { return id_movimiento; }
    public void setId_movimiento(Long id_movimiento) { this.id_movimiento = id_movimiento; }

    public AperturaCaja getAperturaCaja() { return aperturaCaja; }
    public void setAperturaCaja(AperturaCaja aperturaCaja) { this.aperturaCaja = aperturaCaja; }

    public TipoMovimiento getTipo_movimiento() { return tipo_movimiento; }
    public void setTipo_movimiento(TipoMovimiento tipo_movimiento) { this.tipo_movimiento = tipo_movimiento; }

    public CategoriaMovimiento getCategoria() { return categoria; }
    public void setCategoria(CategoriaMovimiento categoria) { this.categoria = categoria; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public Long getId_venta() { return id_venta; }
    public void setId_venta(Long id_venta) { this.id_venta = id_venta; }

    public FormaPago getForma_pago() { return forma_pago; }
    public void setForma_pago(FormaPago forma_pago) { this.forma_pago = forma_pago; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFecha_movimiento() { return fecha_movimiento; }
    public void setFecha_movimiento(LocalDateTime fecha_movimiento) { this.fecha_movimiento = fecha_movimiento; }
}
