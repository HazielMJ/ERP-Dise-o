package com.erp.erp.service;

import com.erp.erp.repository.*;
import com.erp.erp.dto.*;
import com.erp.erp.entity.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Login de usuario - MODO COMPATIBLE
     * Acepta contraseñas en texto plano Y encriptadas
     */
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        
        // Verificar que el usuario esté activo
        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo. Contacte al administrador");
        }
        
        // VERIFICACIÓN COMPATIBLE: Primero intenta BCrypt, luego texto plano
        boolean passwordValida = false;
        boolean necesitaEncriptar = false;
        
        // Si la contraseña está encriptada (empieza con $2a$ o $2b$)
        if (usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")) {
            // Verificar con BCrypt
            passwordValida = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
        } else {
            // La contraseña está en texto plano - comparar directamente
            passwordValida = usuario.getPassword().equals(request.getPassword());
            necesitaEncriptar = true; // Marcar para encriptar después
        }
        
        if (!passwordValida) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        // Si la contraseña estaba en texto plano, encriptarla ahora
        if (necesitaEncriptar) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            usuario.setCambiarPassword(false);
            usuarioRepository.save(usuario);
        }
        
        // ⭐ CARGAR EMPLEADO EXPLÍCITAMENTE (evita problema de @JsonIgnore)
        Empleado empleado = null;
        String rol = "EMPLEADO";
        String nombreEmpleado = usuario.getNombre();
        Integer idEmpleado = null;
        String codigoEmpleado = null;
        
        // Si el usuario tiene un empleado asociado, cargarlo
        if (usuario.getEmpleado() != null) {
            try {
                empleado = empleadoRepository.findById(usuario.getEmpleado().getIdEmpleado())
                    .orElse(null);
                
                if (empleado != null) {
                    rol = empleado.getRol() != null ? empleado.getRol().name() : "EMPLEADO";
                    nombreEmpleado = empleado.getNombreCompleto();
                    idEmpleado = empleado.getIdEmpleado();
                    codigoEmpleado = empleado.getCodigoEmpleado();
                }
            } catch (Exception e) {
                // Si hay error cargando el empleado, usar valores por defecto
                System.out.println("⚠️ Error cargando empleado: " + e.getMessage());
            }
        }
        
        // CONSTRUIR RESPUESTA CON INFORMACIÓN DEL ROL Y EMPLEADO
        return LoginResponse.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .email(usuario.getEmail())
            .activo(usuario.getActivo())
            .cambiarPassword(usuario.getCambiarPassword())
            .mensaje("Login exitoso")
            // NUEVOS CAMPOS
            .rol(rol)
            .idEmpleado(idEmpleado)
            .nombreEmpleado(nombreEmpleado)
            .codigoEmpleado(codigoEmpleado)
            .build();
    }
    
    /**
     * Logout de usuario
     */
    public void logout(Integer usuarioId) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        usuario.setLogout(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }
    
    /**
     * Registro de nuevo usuario
     */
    public Usuario registrarUsuario(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        Usuario usuario = Usuario.builder()
            .nombre(request.getNombre())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .activo(true)
            .cambiarPassword(false)
            .build();
        
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Cambiar contraseña con validación
     */
    public void cambiarPasswordConValidacion(CambiarPasswordRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        // Verificar contraseña actual (compatible con texto plano y BCrypt)
        boolean passwordValida = false;
        if (usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")) {
            passwordValida = passwordEncoder.matches(request.getPasswordActual(), usuario.getPassword());
        } else {
            passwordValida = usuario.getPassword().equals(request.getPasswordActual());
        }
        
        if (!passwordValida) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }
        
        // Actualizar contraseña (siempre encriptada)
        usuario.setPassword(passwordEncoder.encode(request.getPasswordNueva()));
        usuario.setCambiarPassword(false);
        usuarioRepository.save(usuario);
    }
    
    /**
     * Resetear contraseña (para administradores)
     */
    public void resetearPassword(Integer usuarioId, String nuevaPassword) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setCambiarPassword(true);
        usuarioRepository.save(usuario);
    }
    
    // ============================================
    // MÉTODOS CRUD ESTÁNDAR
    // ============================================
    
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        // Solo encriptar si no está ya encriptada
        if (!usuario.getPassword().startsWith("$2a$") && !usuario.getPassword().startsWith("$2b$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = obtenerUsuarioPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setActivo(usuario.getActivo());
        
        // Solo actualizar email si cambió
        if (!existente.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                throw new RuntimeException("El email ya está en uso");
            }
            existente.setEmail(usuario.getEmail());
        }
        
        return usuarioRepository.save(existente);
    }
    
    public void cambiarPassword(Integer id, String passwordActual, String passwordNueva) {
        Usuario usuario = obtenerUsuarioPorId(id);
        
        // Verificar contraseña actual (compatible)
        boolean passwordValida = false;
        if (usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")) {
            passwordValida = passwordEncoder.matches(passwordActual, usuario.getPassword());
        } else {
            passwordValida = usuario.getPassword().equals(passwordActual);
        }
        
        if (!passwordValida) {
            throw new RuntimeException("Password actual incorrecta");
        }
        
        usuario.setPassword(passwordEncoder.encode(passwordNueva));
        usuario.setCambiarPassword(false);
        usuarioRepository.save(usuario);
    }
    
    public void desactivarUsuario(Integer id) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
    
    public void activarUsuario(Integer id) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }
    
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findByActivo(true);
    }
    
    @Transactional(readOnly = true)
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}