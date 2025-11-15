package com.erp.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.erp.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByCodigoProducto(String codigoProducto);
}