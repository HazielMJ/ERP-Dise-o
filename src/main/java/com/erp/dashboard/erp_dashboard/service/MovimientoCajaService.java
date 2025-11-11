package com.erp.dashboard.erp_dashboard.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.erp.dashboard.erp_dashboard.model.MovimientoCaja;
import com.erp.dashboard.erp_dashboard.repository.MovimientoCajaRepository;

@Service
public class MovimientoCajaService {

    private final MovimientoCajaRepository repo;

    public MovimientoCajaService(MovimientoCajaRepository repo) {
        this.repo = repo;
    }

    public List<MovimientoCaja> listar() {
        return repo.findAll();
    }

    public Optional<MovimientoCaja> buscar(Long id) {
        return repo.findById(id);
    }

    public List<MovimientoCaja> listarPorApertura(Long idApertura) {
        return repo.findByAperturaCajaIdApertura(idApertura);
    }

    public MovimientoCaja guardar(MovimientoCaja mov) {
        return repo.save(mov);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}