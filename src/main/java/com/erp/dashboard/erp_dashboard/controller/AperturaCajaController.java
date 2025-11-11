package com.erp.dashboard.erp_dashboard.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.erp.dashboard.erp_dashboard.model.AperturaCaja;
import com.erp.dashboard.erp_dashboard.service.AperturaCajaService;

@RestController
@RequestMapping("/api/aperturas")
@CrossOrigin(origins = "*")
public class AperturaCajaController {

    private final AperturaCajaService service;

    public AperturaCajaController(AperturaCajaService service) {
        this.service = service;
    }

    @GetMapping
    public List<AperturaCaja> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Optional<AperturaCaja> buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public AperturaCaja guardar(@RequestBody AperturaCaja a) { return service.guardar(a); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
