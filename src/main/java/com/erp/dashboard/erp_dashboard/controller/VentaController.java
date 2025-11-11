package com.erp.dashboard.erp_dashboard.controller;

import com.erp.dashboard.erp_dashboard.model.Venta;
import com.erp.dashboard.erp_dashboard.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepo;

    // ✅ Obtener todas las ventas
    @GetMapping
    public List<Venta> listarVentas() {
        return ventaRepo.findAll();
    }

    // ✅ Obtener una venta por ID
    @GetMapping("/{id}")
    public Venta obtenerVenta(@PathVariable Long id) {
        return ventaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // ✅ Crear nueva venta
    @PostMapping
    public Venta crearVenta(@RequestBody Map<String, Object> datos) {
        Venta v = new Venta();
        v.setNumero_venta("VENTA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        v.setId_cliente(1);
        v.setId_usuario(1);

        v.setTipo_pago(Venta.TipoPago.valueOf(
            String.valueOf(datos.get("metodoPago")).toUpperCase()
        ));
        v.setTotal_venta(Double.parseDouble(datos.get("total").toString()));

        return ventaRepo.save(v);
    }

    // ✅ Actualizar venta existente
    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody Map<String, Object> datos) {
        Venta v = ventaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        v.setTipo_pago(Venta.TipoPago.valueOf(
            String.valueOf(datos.get("metodoPago")).toUpperCase()
        ));
        v.setTotal_venta(Double.parseDouble(datos.get("total").toString()));
        return ventaRepo.save(v);
    }

    // ✅ Eliminar venta
    @DeleteMapping("/{id}")
    public Map<String, String> eliminarVenta(@PathVariable Long id) {
        ventaRepo.deleteById(id);
        return Collections.singletonMap("message", "Venta eliminada correctamente");
    }
}
