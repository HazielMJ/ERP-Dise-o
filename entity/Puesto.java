package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;


@Entity
@Table(name = "Puesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto")
    private Integer idPuesto;
    
    @Column(name = "codigo_puesto", unique = true, nullable = false, length = 50)
    private String codigoPuesto;
    
    @Column(name = "nombre_puesto", nullable = false, length = 100)
    private String nombrePuesto;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "nivel_jerarquico")
    private Integer nivelJerarquico;
    
    @Column(name = "salario_minimo", precision = 12, scale = 2)
    private BigDecimal salarioMinimo;
    
    @Column(name = "salario_maximo", precision = 12, scale = 2)
    private BigDecimal salarioMaximo;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVO','INACTIVO') DEFAULT 'ACTIVO'")
    private EstadoPuesto estado = EstadoPuesto.ACTIVO;
    
    public enum EstadoPuesto {
        ACTIVO, INACTIVO
    }
}
