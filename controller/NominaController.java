package com.erp.erp.controller;

import com.erp.erp.entity.Nomina;
import com.erp.erp.service.NominaService;
import com.erp.erp.service.DetalleNominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar la tabla Nómina y Detalle Nómina.
 */
@Controller
@RequestMapping("/nomina")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    @Autowired
    private DetalleNominaService detalleNominaService;

    /**
     * Muestra la lista de nóminas y detalle de nómina.
     */
@GetMapping
public String listar(Model model) {
    model.addAttribute("nominas", nominaService.listarTodos());
    model.addAttribute("detallenomina", detalleNominaService.listarTodos());
    model.addAttribute("nuevaNomina", new Nomina());
    model.addAttribute("activeTab", "nomina");



    return "Nomina";
}
    /**
     * Guarda una nómina y recarga todo.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Nomina nomina) {
        nominaService.guardar(nomina);
        return "redirect:/nomina";
    }

    /**
     * Elimina una nómina.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        nominaService.eliminar(id);
        return "redirect:/nomina";
    }
}
