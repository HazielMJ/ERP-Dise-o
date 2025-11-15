package com.erp.erp.service;

import org.springframework.stereotype.Service;

import com.erp.erp.entity.MovimientoCaja;
import com.erp.erp.repository.MovimientoCajaRepository;

import java.util.List;
import java.util.Optional;

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