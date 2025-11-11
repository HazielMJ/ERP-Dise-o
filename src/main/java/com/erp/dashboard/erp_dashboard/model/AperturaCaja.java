package com.erp.dashboard.erp_dashboard.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apertura_caja")
public class AperturaCaja {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_apertura")
  private Long idApertura;

    private int id_caja;
    private int id_usuario;

    private LocalDateTime fecha_apertura;
    private LocalDateTime fecha_cierre;

    private double saldo_inicial;
    private double saldo_final;
    private double total_ingresos;
    private double total_egresos;
    private double saldo_real;

    @Enumerated(EnumType.STRING)
    private EstadoApertura estado = EstadoApertura.ABIERTA;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // ✅ Relación con movimiento_caja (uno a muchos)
    @OneToMany(mappedBy = "aperturaCaja", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientoCaja> movimientos;

    // ENUM
    public enum EstadoApertura {
        ABIERTA, CERRADA
    }

    // CONSTRUCTORES
    public AperturaCaja() {}

    public AperturaCaja(int id_caja, int id_usuario, double saldo_inicial, String observaciones) {
        this.id_caja = id_caja;
        this.id_usuario = id_usuario;
        this.saldo_inicial = saldo_inicial;
        this.observaciones = observaciones;
        this.fecha_apertura = LocalDateTime.now();
        this.estado = EstadoApertura.ABIERTA;
    }

    // GETTERS Y SETTERS
    public Long getIdApertura() { return idApertura; }
    public void setIdApertura(Long idApertura) { this.idApertura = idApertura; }

    public int getId_caja() { return id_caja; }
    public void setId_caja(int id_caja) { this.id_caja = id_caja; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public LocalDateTime getFecha_apertura() { return fecha_apertura; }
    public void setFecha_apertura(LocalDateTime fecha_apertura) { this.fecha_apertura = fecha_apertura; }

    public LocalDateTime getFecha_cierre() { return fecha_cierre; }
    public void setFecha_cierre(LocalDateTime fecha_cierre) { this.fecha_cierre = fecha_cierre; }

    public double getSaldo_inicial() { return saldo_inicial; }
    public void setSaldo_inicial(double saldo_inicial) { this.saldo_inicial = saldo_inicial; }

    public double getSaldo_final() { return saldo_final; }
    public void setSaldo_final(double saldo_final) { this.saldo_final = saldo_final; }

    public double getTotal_ingresos() { return total_ingresos; }
    public void setTotal_ingresos(double total_ingresos) { this.total_ingresos = total_ingresos; }

    public double getTotal_egresos() { return total_egresos; }
    public void setTotal_egresos(double total_egresos) { this.total_egresos = total_egresos; }

    public double getSaldo_real() { return saldo_real; }
    public void setSaldo_real(double saldo_real) { this.saldo_real = saldo_real; }

    public EstadoApertura getEstado() { return estado; }
    public void setEstado(EstadoApertura estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<MovimientoCaja> getMovimientos() { return movimientos; }
    public void setMovimientos(List<MovimientoCaja> movimientos) { this.movimientos = movimientos; }
}
