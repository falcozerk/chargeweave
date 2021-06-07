package com.falcozerk.chargeweave.beans.charger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargerRepository extends JpaRepository<Charger, Long> {
    Optional<Charger> findById(Long visitId);
//    Page<Charger> findAllByUser(Long userId, Pageable pageable);
//    long countByUser(Long userId);
}
