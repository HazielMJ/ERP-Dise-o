package com.erp.erp.repository;

import com.erp.erp.entity.Contabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContabilidadRepository extends JpaRepository<Contabilidad, Integer> {
}
