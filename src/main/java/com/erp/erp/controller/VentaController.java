package com.erp.erp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.erp.entity.Venta;
import com.erp.erp.service.VentaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Venta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        return service.obtener(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> crear(@Valid @RequestBody Venta v) {
        Venta creada = service.crear(v);
        return ResponseEntity.created(URI.create("/api/ventas/" + creada.getId())).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @Valid @RequestBody Venta v) {
        return ResponseEntity.ok(service.actualizar(id, v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}