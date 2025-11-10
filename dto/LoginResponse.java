package com.erp.erp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Integer id;
    private String nombre;
    private String email;
    private Boolean activo;
    private Boolean cambiarPassword;
    private String mensaje;
    
    
    private String rol;                
    private Integer idEmpleado;        
    private String nombreEmpleado;    
    private String codigoEmpleado;     
}
