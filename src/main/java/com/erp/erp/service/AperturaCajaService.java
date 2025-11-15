package com.erp.erp.service;

import org.springframework.stereotype.Service;

import com.erp.erp.entity.AperturaCaja;
import com.erp.erp.repository.AperturaCajaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AperturaCajaService {

    private final AperturaCajaRepository repo;

    public AperturaCajaService(AperturaCajaRepository repo) {
        this.repo = repo;
    }

    public List<AperturaCaja> listar() {
        return repo.findAll();
    }

    public Optional<AperturaCaja> buscar(Long id) {
        return repo.findById(id);
    }

    public AperturaCaja guardar(AperturaCaja a) {
        return repo.save(a);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
