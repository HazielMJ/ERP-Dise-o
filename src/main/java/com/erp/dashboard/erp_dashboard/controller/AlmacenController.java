package com.erp.dashboard.erp_dashboard.controller;

import com.erp.dashboard.erp_dashboard.model.Almacen;
import com.erp.dashboard.erp_dashboard.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/almacenes")
@CrossOrigin(origins = "*")
public class AlmacenController {

    @Autowired
    private AlmacenRepository almacenRepository;

    @GetMapping
    public List<Almacen> listar() {
        return almacenRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Almacen> obtenerPorId(@PathVariable Long id) {
        return almacenRepository.findById(id);
    }

    @PostMapping
    public Almacen guardar(@RequestBody Almacen almacen) {
        return almacenRepository.save(almacen);
    }

    @PutMapping("/{id}")
    public Almacen actualizar(@PathVariable Long id, @RequestBody Almacen datos) {
        return almacenRepository.findById(id)
                .map(a -> {
                    a.setCodigoAlmacen(datos.getCodigoAlmacen());
                    a.setNombreAlmacen(datos.getNombreAlmacen());
                    a.setUbicacion(datos.getUbicacion());
                    a.setCapacidadTotal(datos.getCapacidadTotal());
                    a.setEspacioUtilizado(datos.getEspacioUtilizado());
                    a.setEstado(datos.getEstado());
                    return almacenRepository.save(a);
                })
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        almacenRepository.deleteById(id);
    }
}
