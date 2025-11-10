package com.erp.erp.service;

import com.erp.erp.entity.Impuesto;
import com.erp.erp.repository.ImpuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImpuestoService {

    @Autowired
    private ImpuestoRepository impuestoRepository;

    public List<Impuesto> listarTodos() {
        return impuestoRepository.findAll();
    }

    public Optional<Impuesto> buscarPorId(Integer id) {
        return impuestoRepository.findById(id);
    }

    public Impuesto guardar(Impuesto impuesto) {
        return impuestoRepository.save(impuesto);
    }

    public void eliminar(Integer id) {
        impuestoRepository.deleteById(id);
    }
}
