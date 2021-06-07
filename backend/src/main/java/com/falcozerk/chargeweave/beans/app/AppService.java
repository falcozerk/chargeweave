package com.falcozerk.chargeweave.beans.app;

import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.charger.ChargerRepository;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.user.UserRepository;
import com.falcozerk.chargeweave.beans.visit.VisitRepository;
import com.falcozerk.chargeweave.integrations.google.ExcelImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService extends CwService {
    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    private VisitRepository visitRepository;

    public String importData() {
        try {
            ExcelImporter importer = new ExcelImporter();
            importer.importXslx();
        } catch( Exception e ) {
            e.printStackTrace();
            return "Could not connect.";
        }

        return "Chargers imported";
    }

    public String clearData() {
        try {
            chargerRepository.deleteAll();
            visitRepository.deleteAll();
//            userRepository.deleteAll();
        } catch( Exception e ) {
            e.printStackTrace();
            return "Could not connect.";
        }

        return "Data deleted";
    }
}
