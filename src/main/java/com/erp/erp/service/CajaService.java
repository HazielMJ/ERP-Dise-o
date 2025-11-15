package com.erp.erp.service;

import org.springframework.stereotype.Service;

import com.erp.erp.entity.Caja;
import com.erp.erp.repository.CajaRepository;

import java.util.List;

@Service
public class CajaService {

    private final CajaRepository cajaRepository;

    public CajaService(CajaRepository cajaRepository) {
        this.cajaRepository = cajaRepository;
    }

    public List<Caja> listar() {
        return cajaRepository.findAll();
    }
    public Caja buscarPorId(Long id) {
        return cajaRepository.findById(id).orElse(null);
    }

    public Caja guardar(Caja caja) {
        return cajaRepository.save(caja);
    }

    public Caja actualizar(Long id, Caja cajaActualizada) {
    Caja caja = cajaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Caja no encontrada con id: " + id));

    caja.setNumeroCaja(cajaActualizada.getNumeroCaja());
    caja.setNombreCaja(cajaActualizada.getNombreCaja());
    caja.setUbicacion(cajaActualizada.getUbicacion());
    caja.setEstado(cajaActualizada.getEstado());
    
    return cajaRepository.save(caja);
}

    public void eliminar(Long id) {
        cajaRepository.deleteById(id);
    }
}