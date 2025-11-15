package com.erp.erp.service;

import com.erp.erp.entity.Almacen;
import com.erp.erp.repository.AlmacenRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlmacenService {

    private final AlmacenRepository almacenRepository;

    public AlmacenService(AlmacenRepository almacenRepository) {
        this.almacenRepository = almacenRepository;
    }

    public List<Almacen> listar() {
        return almacenRepository.findAll();
    }

    public Almacen buscarPorId(Long id) {
        return almacenRepository.findById(id).orElse(null);
    }

    public Almacen guardar(Almacen almacen) {
        return almacenRepository.save(almacen);
    }

    public Almacen actualizar(Long id, Almacen almacenActualizado) {
        Almacen almacen = almacenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Almacén no encontrado con id: " + id));

        almacen.setCodigoAlmacen(almacenActualizado.getCodigoAlmacen());
        almacen.setNombreAlmacen(almacenActualizado.getNombreAlmacen());
        almacen.setUbicacion(almacenActualizado.getUbicacion());
        almacen.setCapacidadTotal(almacenActualizado.getCapacidadTotal());
        almacen.setEspacioUtilizado(almacenActualizado.getEspacioUtilizado());
        almacen.setEstado(almacenActualizado.getEstado());
        
        return almacenRepository.save(almacen);
    }

    public void eliminar(Long id) {
        if (!almacenRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Almacén no encontrado con id: " + id);
        }
        almacenRepository.deleteById(id);
    }
}