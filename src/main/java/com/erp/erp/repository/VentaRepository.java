package com.erp.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.erp.entity.Venta;
import com.erp.erp.entity.Venta.Estado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findByNumeroVenta(String numeroVenta);
    boolean existsByNumeroVenta(String numeroVenta);
    List<Venta> findByEstado(Estado estado);
    List<Venta> findByFechaVentaBetween(LocalDateTime desde, LocalDateTime hasta);
    List<Venta> findByIdCliente(Long idCliente);
}