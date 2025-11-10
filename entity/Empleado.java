package com.erp.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "Empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    
    @Column(name = "codigo_empleado", unique = true, length = 50)
    private String codigoEmpleado;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 100)
    private String apellido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;
    
    @Column(name = "numero_documento", length = 50)
    private String numeroDocumento;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    @Column(length = 13)
    private String rfc;
    
    @Column(length = 18)
    private String curp;
    
    @Column(length = 11)
    private String nss;
    
    @Column(length = 10)
    private String cp;
    
    @Column(length = 200)
    private String direccion;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(length = 100)
    private String correo;
    
    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
    
    @Column(name = "fecha_termino")
    private LocalDate fechaTermino;
    
    @ManyToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;
    
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    
    @Column(name = "salario_base", precision = 12, scale = 2)
    private BigDecimal salarioBase;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contrato")
    private TipoContrato tipoContrato;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Jornada jornada = Jornada.COMPLETA;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVO','INACTIVO','BAJA') DEFAULT 'ACTIVO'")
    private EstadoEmpleado estado = EstadoEmpleado.ACTIVO;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADMIN','GERENTE','VENDEDOR','CONTADOR','ALMACENISTA','RRHH','EMPLEADO') DEFAULT 'EMPLEADO'")
    private RolEmpleado rol = RolEmpleado.EMPLEADO;
    
    public enum TipoDocumento {
        DNI, CURP, RFC, PASAPORTE, OTRO
    }
    
    public enum Genero {
        M, F, OTRO
    }
    
    public enum TipoContrato {
        INDEFINIDO, TEMPORAL, PRACTICAS, HONORARIOS
    }
    
    public enum Jornada {
        COMPLETA, PARCIAL, POR_HORAS
    }
    
    public enum EstadoEmpleado {
        ACTIVO, INACTIVO, BAJA
    }
    
    public enum RolEmpleado {
        ADMIN,          
        GERENTE,        
        VENDEDOR,       
        CONTADOR,       
        ALMACENISTA,    
        RRHH,           
        EMPLEADO       
    }
    
    public String getNombreCompleto() {
        return nombre + (apellido != null ? " " + apellido : "");
    }
}
