package com.erp.erp.service;

import com.erp.erp.entity.Factura;
import com.erp.erp.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // ===========================
    // LISTAR TODAS LAS FACTURAS
    // ===========================
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    // ===========================
    // GUARDAR O ACTUALIZAR FACTURA
    // ===========================
    public void guardar(Factura factura) {
        facturaRepository.save(factura);
    }

    // ===========================
    // BUSCAR FACTURA POR ID
    // ===========================
    public Optional<Factura> buscarPorId(Integer id) {
        return facturaRepository.findById(id);
    }

    // ===========================
    // ELIMINAR FACTURA POR ID
    // ===========================
    public void eliminar(int id) {
        facturaRepository.deleteById(id);
    }
}

