package com.falcozerk.chargeweave.beans.charger;

import com.falcozerk.chargeweave.beans.common.CwBean;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.user.UserRepository;
import com.falcozerk.chargeweave.integrations.google.ExcelImporter;
import com.google.api.services.sheets.v4.Sheets;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargerService extends CwService {
    @Autowired ChargerRepository chargerRepo;

    private static final Logger logger = LoggerFactory.getLogger(ChargerService.class);

    public void importFrom( ExcelImporter pImporter, Workbook pWorkbook) {
        ArrayList<Charger> chargerList = new ArrayList<>();
        pImporter.importSheet( chargerList, pWorkbook, this, pImporter.SUPERCHARGERS_TAB_POS );
        chargerRepo.saveAll( chargerList );
    }

    public CwBean createFrom(ExcelImporter pImporter, ArrayList<Cell> pRow ) {
        pImporter.reset();
        Charger charger = new Charger();

        charger.setSid( pImporter.getNextInteger( pRow ) );
        charger.setName( pImporter.getNextString( pRow ) );
        charger.setStreetAddress( pImporter.getNextString( pRow ) );
        charger.setCity( pImporter.getNextString( pRow ) );
        charger.setState( pImporter.getNextString( pRow ) );
        charger.setZip( pImporter.getNextString( pRow ) );
        charger.setCountry( pImporter.getNextString( pRow ) );
        charger.setStalls( pImporter.getNextInteger( pRow ) );
        charger.setKW( pImporter.getNextDouble( pRow ) );
        charger.setGps( pImporter.getNextString( pRow ) );
        charger.setElevation( pImporter.getNextInteger( pRow ) );
        charger.setStatus( pImporter.getNextString( pRow ) );
        charger.setTsid( pImporter.getNextString( pRow ) );
        charger.setOriginalStalls( pImporter.getNextInteger( pRow ) );
        charger.setAddDate( pImporter.getNextDate( pRow ) );
        charger.setPermitDate( pImporter.getNextDate( pRow ) );
        charger.setPermitDate( pImporter.getNextDate( pRow ) );
        charger.setConstructionDate( pImporter.getNextDate( pRow ) );
        charger.setOpenDate( pImporter.getNextDate( pRow ) );
        charger.setV3UpgradeDate( pImporter.getNextDate( pRow ) );
        charger.setV3UpgradeDate( pImporter.getNextDate( pRow ) );
        charger.setStallsUpgradeDate( pImporter.getNextDate( pRow ) );
        charger.setStallsUpgradeDate( pImporter.getNextDate( pRow ) );
        charger.setClosedDate( pImporter.getNextDate( pRow ) );
        charger.setType( pImporter.getNextString( pRow ) );
        charger.setTeslaUrl( pImporter.getNextString( pRow ) );
        charger.setDiscussUrl( pImporter.getNextString( pRow ) );
        charger.setFirstVisitDate( pImporter.getNextDate( pRow ) );
        charger.setLastActiveDate( pImporter.getNextDate( pRow ) );
        charger.setDaysFromPermitToConstruction( pImporter.getNextInteger( pRow ) );
        charger.setDaysFromConstructionToOpen( pImporter.getNextInteger( pRow ) );
        charger.setDaysFromConstructionToOpen( pImporter.getNextInteger( pRow ) );
        charger.setDaysFromOpenToFirst( pImporter.getNextInteger( pRow ) );
        charger.setRegion( pImporter.getNextString( pRow ) );
        charger.setSort( pImporter.getNextString( pRow ) );
        charger.setLatitude( pImporter.getNextString( pRow ) );
        charger.setLongitude( pImporter.getNextString( pRow ) );
        charger.setVisits( pImporter.getNextInteger( pRow ) );
        charger.setVisits2013( pImporter.getNextInteger( pRow ) );
        charger.setVisits2014( pImporter.getNextInteger( pRow ) );
        charger.setVisits2015( pImporter.getNextInteger( pRow ) );
        charger.setVisits2016( pImporter.getNextInteger( pRow ) );
        charger.setVisits2017( pImporter.getNextInteger( pRow ) );
        charger.setVisits2018( pImporter.getNextInteger( pRow ) );
        charger.setVisits2019( pImporter.getNextInteger( pRow ) );
        charger.setVisits2020( pImporter.getNextInteger( pRow ) );
        charger.setVisits2021( pImporter.getNextInteger( pRow ) );

        return charger;
    }

}
