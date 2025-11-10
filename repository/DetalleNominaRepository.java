package com.erp.erp.repository;

import com.erp.erp.entity.DetalleNomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleNominaRepository extends JpaRepository<DetalleNomina, Integer> {
}
