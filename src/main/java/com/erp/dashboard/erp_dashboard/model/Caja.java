package com.erp.dashboard.erp_dashboard.model;
import jakarta.persistence.*;

@Entity
@Table(name = "caja", indexes = {
    @Index(name = "idx_numero", columnList = "numero_caja")
})
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caja")
    private Long idCaja;

    @Column(name = "numero_caja", length = 50, nullable = false, unique = true)
    private String numeroCaja;

    @Column(name = "nombre_caja", length = 100, nullable = false)
    private String nombreCaja;

    @Column(length = 100)
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCaja estado = EstadoCaja.ACTIVA;


    public Caja() {}

    public Caja(String numeroCaja, String nombreCaja, String ubicacion, EstadoCaja estado) {
        this.numeroCaja = numeroCaja;
        this.nombreCaja = nombreCaja;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Getters y setters
    public Long getIdCaja() { return idCaja; }
    public void setIdCaja(Long idCaja) { this.idCaja = idCaja; }

    public String getNumeroCaja() { return numeroCaja; }
    public void setNumeroCaja(String numeroCaja) { this.numeroCaja = numeroCaja; }

    public String getNombreCaja() { return nombreCaja; }
    public void setNombreCaja(String nombreCaja) { this.nombreCaja = nombreCaja; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public EstadoCaja getEstado() { return estado; }
    public void setEstado(EstadoCaja estado) { this.estado = estado; }

    public enum EstadoCaja {
        ACTIVA,
        INACTIVA,
        MANTENIMIENTO
    }
}