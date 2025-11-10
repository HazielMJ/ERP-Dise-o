package com.erp.erp.controller;

import com.erp.erp.entity.DetalleNomina;
import com.erp.erp.service.DetalleNominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detallenomina")
public class DetalleNominaController {

    @Autowired
    private DetalleNominaService detalleNominaService;

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleNomina detalleNomina) {
        detalleNominaService.guardar(detalleNomina);
        return "redirect:/nomina"; // ✅ Redirige a /nomina para recargar datos
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detalleNominaService.eliminar(id);
        return "redirect:/nomina"; // ✅ Igual aquí
    }
}
