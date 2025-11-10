package com.erp.erp.service;

import com.erp.erp.entity.DetalleContable;
import com.erp.erp.repository.DetalleContableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DetalleContableService {

    @Autowired
    private DetalleContableRepository detalleContableRepository;

    public List<DetalleContable> listarTodos() {
        return detalleContableRepository.findAll();
    }

    public void guardar(DetalleContable detalle) {
        if (detalle.getDebe() == null) detalle.setDebe(BigDecimal.ZERO);
        if (detalle.getHaber() == null) detalle.setHaber(BigDecimal.ZERO);

        detalle.setDebe(detalle.getDebe().setScale(2, RoundingMode.HALF_UP));
        detalle.setHaber(detalle.getHaber().setScale(2, RoundingMode.HALF_UP));

        detalleContableRepository.save(detalle);
    }

    public void eliminar(int id) {
        detalleContableRepository.deleteById(id);
    }
}
