package com.erp.erp.controller;

import com.erp.erp.entity.Almacen;
import com.erp.erp.service.AlmacenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    private final AlmacenService almacenService;

    public AlmacenController(AlmacenService almacenService) {
        this.almacenService = almacenService;
    }

    @GetMapping
    public List<Almacen> listar() {
        return almacenService.listar();
    }

    @GetMapping("/{id}")
public ResponseEntity<Almacen> obtenerAlmacenPorId(@PathVariable Long id) {
    Almacen almacen = almacenService.buscarPorId(id); 
    if (almacen != null) {
        return ResponseEntity.ok(almacen);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Almacen almacen) {
        try {
            Almacen nuevoAlmacen = almacenService.guardar(almacen);
            return ResponseEntity.ok(nuevoAlmacen);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error al guardar: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Almacen almacenActualizado) {
        try {
            Almacen almacen = almacenService.actualizar(id, almacenActualizado);
            return ResponseEntity.ok(almacen);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            almacenService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al eliminar: " + e.getMessage());
        }
    }
}