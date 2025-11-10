package com.erp.erp.controller;

import com.erp.erp.entity.Factura;
import com.erp.erp.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el módulo de Facturación
 * Permite listar, crear, editar y eliminar facturas
 */
@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    /**
     * Muestra la vista principal de facturación con la lista de facturas
     * y un objeto vacío para el formulario (evita error Thymeleaf)
     */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaService.listarFacturas());
        model.addAttribute("factura", new Factura()); // 🔹 Previene el error EL
        return "Facturacion";
    }

    /**
     * Guarda o actualiza una factura
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("factura") Factura factura) {
        facturaService.guardar(factura);
        return "redirect:/factura";
    }

    /**
     * Carga los datos de una factura existente para edición
     */
    @GetMapping("/editar/{id_factura}")
    public String editar(@PathVariable("id_factura") Integer id_factura, Model model) {
        Factura factura = facturaService.buscarPorId(id_factura).orElse(new Factura());
model.addAttribute("factura", factura);
model.addAttribute("facturas", facturaService.listarFacturas());
return "Facturacion";

    }

    /**
     * Elimina una factura por su ID
     */
    @GetMapping("/eliminar/{id_factura}")
    public String eliminar(@PathVariable("id_factura") Integer id_factura) {
        facturaService.eliminar(id_factura);
        return "redirect:/factura";
    }
}
