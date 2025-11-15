package com.erp.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.erp.entity.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}