package com.falcozerk.chargeweave.beans.visit;

import com.falcozerk.chargeweave.beans.common.CwBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    Optional<Visit> findById(Long visitId);
    Page<Visit> findAllByUser(Long userId, Pageable pageable);
    long countByUser(Long userId);
}

