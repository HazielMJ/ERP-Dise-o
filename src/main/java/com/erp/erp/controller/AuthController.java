package com.erp.erp.controller;

import com.erp.erp.dto.*;
import com.erp.erp.entity.Usuario;
import com.erp.erp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final UsuarioService usuarioService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = usuarioService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("success", "false");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
    
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroRequest request) {
        try {
            Usuario usuario = usuarioService.registrarUsuario(request);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("mensaje", "Usuario registrado exitosamente");
            response.put("usuario", Map.of(
                "id", usuario.getId(),
                "nombre", usuario.getNombre(),
                "email", usuario.getEmail()
            ));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @PostMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable Integer id) {
        try {
            usuarioService.logout(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("mensaje", "Logout exitoso");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@Valid @RequestBody CambiarPasswordRequest request) {
        try {
            usuarioService.cambiarPasswordConValidacion(request);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("mensaje", "Contraseña actualizada exitosamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @PostMapping("/resetear-password/{id}")
    public ResponseEntity<?> resetearPassword(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        try {
            String nuevaPassword = body.get("nuevaPassword");
            if (nuevaPassword == null || nuevaPassword.isEmpty()) {
                throw new RuntimeException("La nueva contraseña es obligatoria");
            }
            
            if (nuevaPassword.length() < 6) {
                throw new RuntimeException("La contraseña debe tener al menos 6 caracteres");
            }
            
            usuarioService.resetearPassword(id, nuevaPassword);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("mensaje", "Contraseña reseteada. El usuario deberá cambiarla en el próximo login");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @GetMapping("/verificar/{email}")
    public ResponseEntity<?> verificarEmail(@PathVariable String email) {
        boolean existe = usuarioService.existeEmail(email);
        Map<String, Object> response = new HashMap<>();
        response.put("existe", existe);
        response.put("disponible", !existe);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("mensaje", "Auth API funcionando correctamente");
        return ResponseEntity.ok(response);
    }
}