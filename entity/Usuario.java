package com.erp.erp.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Builder.Default
    @Column(name = "Activo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean activo = true;
    
    @Column(name = "Logout")
    private LocalDateTime logout;
    
    @Builder.Default
    @Column(name = "CambiarPassword", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean cambiarPassword = false;
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @OneToOne
    @JoinColumn(name = "id_empleado", unique = true)
    private Empleado empleado;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
    
    public String getRol() {
        if (empleado != null && empleado.getRol() != null) {
            return empleado.getRol().name();
        }
        return "EMPLEADO"; 
    }
    
    public String getNombreEmpleado() {
        if (empleado != null) {
            return empleado.getNombreCompleto();
        }
        return nombre; 
    }
}
