package com.erp.dashboard.erp_dashboard.controller;

import com.erp.dashboard.erp_dashboard.model.Caja;
import com.erp.dashboard.erp_dashboard.service.CajaService;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public Caja guardar(@RequestBody Caja caja) {
        return cajaService.guardar(caja);
    }

    @PutMapping("/{id}")
    public Caja actualizar(@PathVariable Long id, @RequestBody Caja caja) {
        return cajaService.actualizar(id, caja);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cajaService.eliminar(id);
    }
}