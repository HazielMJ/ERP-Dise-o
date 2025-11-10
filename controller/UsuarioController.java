package com.erp.erp.controller;

import com.erp.erp.entity.*;
import com.erp.erp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(usuarioService.crearUsuario(usuario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }
    
    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<Void> cambiarPassword(@PathVariable Integer id, @RequestBody Map<String, String> passwords) {
        usuarioService.cambiarPassword(id, passwords.get("passwordActual"), passwords.get("passwordNueva"));
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Integer id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }
    
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosActivos() {
        return ResponseEntity.ok(usuarioService.obtenerUsuariosActivos());
    }
}
