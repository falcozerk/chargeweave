package com.falcozerk.chargeweave.beans.visit;

import com.falcozerk.chargeweave.auth.CurrentUser;
import com.falcozerk.chargeweave.auth.UserPrincipal;
import com.falcozerk.chargeweave.beans.common.CwController;
import com.falcozerk.chargeweave.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
public class VisitController extends CwController {
    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);

    @Autowired
    private VisitRepository visitRepo;

    @GetMapping
    public Page<Visit> GetPage(@CurrentUser UserPrincipal currentUser,
                               @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                               @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return visitRepo.findAllByUser(currentUser.getId(), PageRequest.of(page, size));
    }

    @GetMapping("/{visitId}")
    public Optional<Visit> getVisitById(@PathVariable Long visitId) {
        return visitRepo.findById(visitId);
    }

}
