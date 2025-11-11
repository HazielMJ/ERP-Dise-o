package com.erp.dashboard.erp_dashboard.service;

import com.erp.dashboard.erp_dashboard.model.Caja;
import com.erp.dashboard.erp_dashboard.repository.CajaRepository;
import org.springframework.stereotype.Service;
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

    public Caja guardar(Caja caja) {
        return cajaRepository.save(caja);
    }

    public Caja actualizar(Long id, Caja cajaActualizada) {
        return cajaRepository.findById(id)
            .map(caja -> {
                caja.setNumeroCaja(cajaActualizada.getNumeroCaja());
                caja.setNombreCaja(cajaActualizada.getNombreCaja());
                caja.setUbicacion(cajaActualizada.getUbicacion());
                caja.setEstado(cajaActualizada.getEstado());
                return cajaRepository.save(caja);
            }).orElse(null);
    }

    public void eliminar(Long id) {
        cajaRepository.deleteById(id);
    }
}