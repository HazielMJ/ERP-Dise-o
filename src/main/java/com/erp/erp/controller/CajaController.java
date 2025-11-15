package com.erp.erp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.erp.entity.Caja;
import com.erp.erp.service.CajaService;

import java.util.List;

@RestController
@RequestMapping("/api/cajas")
@CrossOrigin(origins = "*")
public class CajaController {

    private final CajaService cajaService;

    public CajaController(CajaService cajaService) {
        this.cajaService = cajaService;
    }

    @GetMapping
    public List<Caja> listar() {
        return cajaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caja> obtenerCajaPorId(@PathVariable Long id) {
        Caja caja = cajaService.buscarPorId(id); 
        if (caja != null) {
            return ResponseEntity.ok(caja);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Caja caja) {
        try {
            Caja nuevaCaja = cajaService.guardar(caja);
            return ResponseEntity.ok(nuevaCaja);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error al guardar: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Caja cajaActualizada) {
        try {
            Caja caja = cajaService.actualizar(id, cajaActualizada);
            return ResponseEntity.ok(caja);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            cajaService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar: " + e.getMessage());
        }
    }
}