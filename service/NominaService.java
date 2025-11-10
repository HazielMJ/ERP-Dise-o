package com.erp.erp.service;

import com.erp.erp.entity.Nomina;
import com.erp.erp.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NominaService {

    @Autowired
    private NominaRepository nominaRepository;

    public List<Nomina> listarTodos() {
        return nominaRepository.findAll();
    }

    public Nomina guardar(Nomina nomina) {
        return nominaRepository.save(nomina);
    }

    public void eliminar(Integer id) {
        nominaRepository.deleteById(id);
    }
}
