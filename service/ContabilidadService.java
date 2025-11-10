package com.erp.erp.service;

import com.erp.erp.entity.Contabilidad;
import com.erp.erp.repository.ContabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContabilidadService {

    @Autowired
    private ContabilidadRepository contabilidadRepository;

    public List<Contabilidad> listarTodos() {
        return contabilidadRepository.findAll();
    }

    public Optional<Contabilidad> buscarPorId(Integer id) {
        return contabilidadRepository.findById(id);
    }

    public Contabilidad guardar(Contabilidad contabilidad) {
        return contabilidadRepository.save(contabilidad);
    }

    public void eliminar(Integer id) {
        contabilidadRepository.deleteById(id);
    }
}
