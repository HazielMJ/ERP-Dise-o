package com.erp.erp.controller;

import com.erp.erp.entity.DetalleContable;
import com.erp.erp.service.DetalleContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@RequestMapping("/detallecontable")
public class DetalleContableController {

    @Autowired
    private DetalleContableService detalleContableService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detallecontable", detalleContableService.listarTodos());
        model.addAttribute("nuevoDetalle", new DetalleContable());
        return "Contabilidad";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleContable detalle, RedirectAttributes redirectAttrs) {
        try {
            // Normaliza los valores
            if (detalle.getDebe() == null) detalle.setDebe(BigDecimal.ZERO);
            if (detalle.getHaber() == null) detalle.setHaber(BigDecimal.ZERO);

            detalle.setDebe(detalle.getDebe().setScale(2, RoundingMode.HALF_UP));
            detalle.setHaber(detalle.getHaber().setScale(2, RoundingMode.HALF_UP));

            detalleContableService.guardar(detalle);
            redirectAttrs.addFlashAttribute("exito", "Registro guardado correctamente.");

        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }

        return "redirect:/detallecontable";
    }

    @GetMapping("/eliminar/{id_detalle}")
    public String eliminar(@PathVariable int id_detalle, RedirectAttributes redirectAttrs) {
        try {
            detalleContableService.eliminar(id_detalle);
            redirectAttrs.addFlashAttribute("exito", "Registro eliminado correctamente.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/detallecontable";
    }
}
