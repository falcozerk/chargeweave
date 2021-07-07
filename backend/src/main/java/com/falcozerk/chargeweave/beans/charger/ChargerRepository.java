package com.falcozerk.chargeweave.beans.charger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    Optional<Charger> findById(Long id);
    Optional<Charger> findBySid(Long chargerId);
//    Page<Charger> findAllByUser(Integer userId, Pageable pageable);
//    long countByUser(Integer userId);
}
