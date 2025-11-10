package com.erp.erp.service;

import com.erp.erp.entity.DetalleNomina;
import com.erp.erp.repository.DetalleNominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleNominaService {

    @Autowired
    private DetalleNominaRepository detalleNominaRepository;

    public List<DetalleNomina> listarTodos() {
        return detalleNominaRepository.findAll();
    }

    public Optional<DetalleNomina> buscarPorId(Integer id) {
        return detalleNominaRepository.findById(id);
    }

    public DetalleNomina guardar(DetalleNomina detalleNomina) {
        return detalleNominaRepository.save(detalleNomina);
    }

    public void eliminar(Integer id) {
        detalleNominaRepository.deleteById(id);
    }
}
