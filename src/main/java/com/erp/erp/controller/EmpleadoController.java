package com.erp.erp.controller;

import com.erp.erp.entity.*;
import com.erp.erp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@Valid @RequestBody Empleado empleado) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(empleadoService.crearEmpleado(empleado));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(id, empleado));
    }
    
    @PostMapping("/{id}/dar-baja")
    public ResponseEntity<Void> darBajaEmpleado(@PathVariable Integer id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        empleadoService.darBajaEmpleado(id, fechaTermino);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleado(@PathVariable Integer id) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoPorId(id));
    }
    
    @GetMapping("/activos")
    public ResponseEntity<List<Empleado>> obtenerEmpleadosActivos() {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadosActivos());
    }
    
    @GetMapping("/departamento/{departamentoId}")
    public ResponseEntity<List<Empleado>> obtenerEmpleadosPorDepartamento(@PathVariable Integer departamentoId) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadosPorDepartamento(departamentoId));
    }
}