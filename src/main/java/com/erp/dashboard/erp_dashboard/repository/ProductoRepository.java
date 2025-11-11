package com.erp.dashboard.erp_dashboard.repository;

import com.erp.dashboard.erp_dashboard.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByCodigoProducto(String codigoProducto);
}