package com.erp.dashboard.erp_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.dashboard.erp_dashboard.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}