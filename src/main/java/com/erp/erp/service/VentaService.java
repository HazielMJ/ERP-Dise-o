package com.erp.erp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.erp.entity.Venta;
import com.erp.erp.repository.VentaRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository repo;

    public VentaService(VentaRepository repo) {
        this.repo = repo;
    }

    public List<Venta> listar() { return repo.findAll(); }

    public Optional<Venta> obtener(Long id) { return repo.findById(id); }

    @Transactional
public Venta crear(Venta v) {
    if (v.getNumeroVenta() == null || v.getNumeroVenta().isBlank()) {
        v.setNumeroVenta(generarNumeroVenta()); // p.ej. "V-20251113160501"
    }
    return repo.save(v);
}

    @Transactional
    public Venta actualizar(Long id, Venta cambios) {
        Venta v = repo.findById(id).orElseThrow();
        v.setIdCliente(cambios.getIdCliente());
        v.setIdUsuario(cambios.getIdUsuario());
        v.setSubtotal(cambios.getSubtotal());
        v.setImpuestos(cambios.getImpuestos());
        v.setDescuento(cambios.getDescuento());
        v.setTipoPago(cambios.getTipoPago());
        v.setMontoRecibido(cambios.getMontoRecibido());
        v.setEstado(cambios.getEstado());
        v.setObservaciones(cambios.getObservaciones());
        // numeroVenta se mantiene o podrías permitir editarlo si tu negocio lo permite
        return repo.save(v);
    }

    @Transactional
    public void eliminar(Long id) { repo.deleteById(id); }

    private String generarNumeroVenta() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "VEN-" + fecha;
    }
}