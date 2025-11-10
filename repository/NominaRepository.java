package com.erp.erp.repository;

import com.erp.erp.entity.Nomina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Integer> {
}
