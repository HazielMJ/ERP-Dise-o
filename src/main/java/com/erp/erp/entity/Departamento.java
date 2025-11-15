package com.erp.erp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Departamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer idDepartamento;
    
    @Column(name = "codigo_departamento", unique = true, nullable = false, length = 50)
    private String codigoDepartamento;
    
    @Column(name = "nombre_departamento", nullable = false, length = 100)
    private String nombreDepartamento;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "departamento_padre_id")
    private Departamento departamentoPadre;
    
    @ManyToOne
    @JoinColumn(name = "id_jefe")
    private Empleado jefe;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVO','INACTIVO') DEFAULT 'ACTIVO'")
    private EstadoDepartamento estado = EstadoDepartamento.ACTIVO;
    
    public enum EstadoDepartamento {
        ACTIVO, INACTIVO
    }
}