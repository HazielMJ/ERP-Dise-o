package com.erp.erp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;

    @Column(nullable = false, length = 50)
    private String numero_factura;

    private String serie;
    private Integer folio;
    private LocalDate fecha_emision;
    private LocalDate fecha_vencimiento;

    private int id_cliente;
    private int id_venta;
     private int id_compra; 
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal descuentos;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private TipoFactura tipo_factura;

    @Enumerated(EnumType.STRING)
    private FormaPago forma_pago;

    private String uso_cfdi;
    private String metodo_pago;
    private String uuid_fiscal;
    private int id_usuario;
    private LocalDateTime fecha_creacion;

    // ENUMS definidos correctamente
    public enum Estado {
        PENDIENTE, PAGADA, VENCIDA, ANULADA
    }

    public enum TipoFactura {
        VENTA, COMPRA
    }

    public enum FormaPago {
        EFECTIVO, TARJETA, TRANSFERENCIA, MIXTO
    }
}
