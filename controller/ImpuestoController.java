package com.erp.erp.controller;

import com.erp.erp.entity.Impuesto;
import com.erp.erp.service.ImpuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/impuesto")
public class ImpuestoController {

    @Autowired
    private ImpuestoService impuestoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("impuestos", impuestoService.listarTodos());
        model.addAttribute("nuevoImpuesto", new Impuesto());
        return "Impuesto";
    }

@PostMapping("/guardar")
public String guardar(@ModelAttribute Impuesto impuesto) {
    // Si el ID viene vacío, se crea un nuevo registro
    if (impuesto.getId_impuesto() == null) {
        impuestoService.guardar(impuesto);
    } else {
        // Si existe, se actualiza el registro existente
        Impuesto existente = impuestoService.buscarPorId(impuesto.getId_impuesto()).orElse(null);
        if (existente != null) {
            existente.setNombre(impuesto.getNombre());
            existente.setTipo(impuesto.getTipo());
            existente.setTasa(impuesto.getTasa());
            existente.setDescripcion(impuesto.getDescripcion());
            existente.setEstado(impuesto.getEstado());
            impuestoService.guardar(existente);
        } else {
            impuestoService.guardar(impuesto);
        }
    }
    return "redirect:/impuesto";
}


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        impuestoService.eliminar(id);
        return "redirect:/impuesto";
    }
    @GetMapping("/editar/{id}")
public String editar(@PathVariable Integer id, Model model) {
    Impuesto impuesto = impuestoService.buscarPorId(id).orElse(new Impuesto());
    model.addAttribute("impuestos", impuestoService.listarTodos());
    model.addAttribute("nuevoImpuesto", impuesto);
    return "Impuesto";
}

}
