package com.erp.dashboard.erp_dashboard.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.erp.dashboard.erp_dashboard.model.MovimientoCaja;
import com.erp.dashboard.erp_dashboard.service.MovimientoCajaService;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoCajaController {

    private final MovimientoCajaService service;

    public MovimientoCajaController(MovimientoCajaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimientoCaja> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Optional<MovimientoCaja> buscar(@PathVariable Long id) { return service.buscar(id); }

    @GetMapping("/apertura/{idApertura}")
    public List<MovimientoCaja> listarPorApertura(@PathVariable Long idApertura) {
        return service.listarPorApertura(idApertura);
    }

    @PostMapping
    public MovimientoCaja guardar(@RequestBody MovimientoCaja mov) {
        return service.guardar(mov);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
