package com.erp.erp.controller;

import com.erp.erp.entity.Contabilidad;
import com.erp.erp.entity.DetalleContable;
import com.erp.erp.service.ContabilidadService;
import com.erp.erp.service.DetalleContableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contabilidad")
public class ContabilidadController {

    @Autowired
    private ContabilidadService contabilidadService;
@Autowired
private DetalleContableService detalleContableService;

 @GetMapping
public String listar(Model model) {
    model.addAttribute("contabilidades", contabilidadService.listarTodos());
    model.addAttribute("nuevaContabilidad", new Contabilidad());
    model.addAttribute("detallecontable", detalleContableService.listarTodos());
    model.addAttribute("nuevoDetalle", new DetalleContable());
    return "Contabilidad";
}

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contabilidad contabilidad) {
        contabilidadService.guardar(contabilidad);
        return "redirect:/contabilidad";
    }

    @GetMapping("/eliminar/{id_asiento}")
    public String eliminar(@PathVariable int id_asiento) {
        contabilidadService.eliminar(id_asiento);
        return "redirect:/contabilidad";
    }
}
