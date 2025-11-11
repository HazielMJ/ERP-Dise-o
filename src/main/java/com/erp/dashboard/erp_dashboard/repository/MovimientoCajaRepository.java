package com.erp.dashboard.erp_dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.dashboard.erp_dashboard.model.MovimientoCaja;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimientoCajaRepository extends JpaRepository<MovimientoCaja, Long> {

  List<MovimientoCaja> findByAperturaCajaIdApertura(Long idApertura); // usa propiedad Java

  @Query("select m from MovimientoCaja m where m.aperturaCaja.idApertura = :id")
  List<MovimientoCaja> buscarPorApertura(@Param("id") Long id);
}