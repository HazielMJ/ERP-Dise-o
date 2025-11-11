package com.erp.dashboard.erp_dashboard.service;

import com.erp.dashboard.erp_dashboard.model.Venta;
import com.erp.dashboard.erp_dashboard.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // ✅ Listar todas las ventas
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    // ✅ Guardar una nueva venta
    public Venta guardar(Venta venta) {
        venta.setFecha_venta(LocalDateTime.now()); // usa el nombre correcto
        return ventaRepository.save(venta);
    }

    // ✅ Editar una venta existente
    public Venta editar(Long id, Venta venta) {
        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // 🔹 Actualiza los campos según tu entidad
        existente.setNumero_venta(venta.getNumero_venta());
        existente.setId_cliente(venta.getId_cliente());
        existente.setId_usuario(venta.getId_usuario());
        existente.setSubtotal(venta.getSubtotal());
        existente.setImpuestos(venta.getImpuestos());
        existente.setDescuento(venta.getDescuento());
        existente.setTotal_venta(venta.getTotal_venta());
        existente.setEstado(venta.getEstado());
        existente.setTipo_pago(venta.getTipo_pago());
        existente.setMonto_recibido(venta.getMonto_recibido());
        existente.setCambio(venta.getCambio());
        existente.setObservaciones(venta.getObservaciones());

        return ventaRepository.save(existente);
    }

    // ✅ Eliminar una venta
    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada");
        }
        ventaRepository.deleteById(id);
    }
}
