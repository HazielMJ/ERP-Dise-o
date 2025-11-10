package com.erp.erp.service;

import com.erp.erp.entity.*;
import com.erp.erp.repository.*;
import com.erp.erp.dto.*;
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
    private final PasswordEncoder passwordEncoder;
    
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        
        if (!usuario.getActivo()) {
            throw new RuntimeException("Usuario inactivo. Contacte al administrador");
        }
        
        boolean passwordValida = false;
        boolean necesitaEncriptar = false;
        
        if (usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")) {
            passwordValida = passwordEncoder.matches(request.getPassword(), usuario.getPassword());
        } else {
            passwordValida = usuario.getPassword().equals(request.getPassword());
            necesitaEncriptar = true; 
        }
        
        if (!passwordValida) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        if (necesitaEncriptar) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            usuario.setCambiarPassword(false);
            usuarioRepository.save(usuario);
        }
        
        return LoginResponse.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .email(usuario.getEmail())
            .activo(usuario.getActivo())
            .cambiarPassword(usuario.getCambiarPassword())
            .mensaje("Login exitoso")
            .rol(usuario.getRol())
            .idEmpleado(usuario.getEmpleado() != null ? usuario.getEmpleado().getIdEmpleado() : null)
            .nombreEmpleado(usuario.getEmpleado() != null ? usuario.getEmpleado().getNombreCompleto() : usuario.getNombre())
            .codigoEmpleado(usuario.getEmpleado() != null ? usuario.getEmpleado().getCodigoEmpleado() : null)
            .build();
    }
    
    public void logout(Integer usuarioId) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        usuario.setLogout(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }
    
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
    
    public void cambiarPasswordConValidacion(CambiarPasswordRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordValida = false;
        if (usuario.getPassword().startsWith("$2a$") || usuario.getPassword().startsWith("$2b$")) {
            passwordValida = passwordEncoder.matches(request.getPasswordActual(), usuario.getPassword());
        } else {
            passwordValida = usuario.getPassword().equals(request.getPasswordActual());
        }
        
        if (!passwordValida) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }
        
        usuario.setPassword(passwordEncoder.encode(request.getPasswordNueva()));
        usuario.setCambiarPassword(false);
        usuarioRepository.save(usuario);
    }
    
    public void resetearPassword(Integer usuarioId, String nuevaPassword) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setCambiarPassword(true);
        usuarioRepository.save(usuario);
    }
  
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (!usuario.getPassword().startsWith("$2a$") && !usuario.getPassword().startsWith("$2b$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = obtenerUsuarioPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setActivo(usuario.getActivo());
        
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
