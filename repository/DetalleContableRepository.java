package com.erp.erp.repository;

import com.erp.erp.entity.DetalleContable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleContableRepository extends JpaRepository<DetalleContable, Integer> {
}
