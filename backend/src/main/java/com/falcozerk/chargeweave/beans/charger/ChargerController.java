package com.falcozerk.chargeweave.beans.charger;

import com.falcozerk.chargeweave.beans.app.AppService;
import com.falcozerk.chargeweave.beans.common.CwController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/chargers")
public class ChargerController extends CwController {
    private static final Logger logger = LoggerFactory.getLogger(ChargerController.class);

    @Autowired
    private ChargerRepository chargerRepo;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private AppService appService;

    @GetMapping("/{chargerId}")
    public Optional<Charger> getChargerById(@PathVariable Long chargerId) {
        return chargerRepo.findById(chargerId);
    }

    @GetMapping("/importData")
    public String importData() {
        return appService.importData();
    }

    @GetMapping("/clearData")
    public String clearData() {
        return appService.clearData();
    }


}
