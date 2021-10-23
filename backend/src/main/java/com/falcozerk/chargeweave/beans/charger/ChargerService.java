package com.falcozerk.chargeweave.beans.charger;

import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.app.Importer;
import com.falcozerk.chargeweave.beans.visit.VisitService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChargerService extends CwService {
    @Autowired
    ChargerRepository chargerRepo;

    static final Logger logger = LoggerFactory.getLogger(ChargerService.class);

    public void importFrom(Importer pImporter, Workbook pWorkbook, int pTabId) {
        pImporter.init( pWorkbook, pTabId );
        ArrayList<Cell> cellList = new ArrayList<>();

        while( pImporter.importRow( cellList ) ) {
            chargerRepo.save( createFrom( pImporter, cellList ) );
        }
    }

    public Charger createFrom(Importer pImporter, ArrayList<Cell> pCellList ) {
        Charger charger = new Charger();

        charger.setSid( pImporter.getNextLong( pCellList ) );
        charger.setName( pImporter.getNextString( pCellList ) );
        charger.setStreetAddress( pImporter.getNextString( pCellList ) );
        charger.setCity( pImporter.getNextString( pCellList ) );
        charger.setState( pImporter.getNextString( pCellList ) );
        charger.setZip( pImporter.getNextString( pCellList ) );
        charger.setCountry( pImporter.getNextString( pCellList ) );
        charger.setStalls( pImporter.getNextInteger( pCellList ) );
        charger.setKW( pImporter.getNextDouble( pCellList ) );
        charger.setGps( pImporter.getNextString( pCellList ) );
        charger.setElevation( pImporter.getNextInteger( pCellList ) );
        charger.setStatus( pImporter.getNextString( pCellList ) );
        charger.setTsid( pImporter.getNextString( pCellList ) );
        charger.setOriginalStalls( pImporter.getNextInteger( pCellList ) );
        charger.setAddDate( pImporter.getNextDate( pCellList ) );
        charger.setPermitDate( pImporter.getNextDate( pCellList ) );
        charger.setPermitDate( pImporter.getNextDate( pCellList ) );
        charger.setConstructionDate( pImporter.getNextDate( pCellList ) );
        charger.setOpenDate( pImporter.getNextDate( pCellList ) );
        charger.setV3UpgradeDate( pImporter.getNextDate( pCellList ) );
        charger.setV3UpgradeDate( pImporter.getNextDate( pCellList ) );
        charger.setStallsUpgradeDate( pImporter.getNextDate( pCellList ) );
        charger.setStallsUpgradeDate( pImporter.getNextDate( pCellList ) );
        charger.setClosedDate( pImporter.getNextDate( pCellList ) );
        charger.setType( pImporter.getNextString( pCellList ) );
        charger.setTeslaUrl( pImporter.getNextString( pCellList ) );
        charger.setDiscussUrl( pImporter.getNextString( pCellList ) );
        charger.setFirstVisitDate( pImporter.getNextDate( pCellList ) );
        charger.setLastActiveDate( pImporter.getNextDate( pCellList ) );
        charger.setDaysFromPermitToConstruction( pImporter.getNextInteger( pCellList ) );
        charger.setDaysFromConstructionToOpen( pImporter.getNextInteger( pCellList ) );
        charger.setDaysFromConstructionToOpen( pImporter.getNextInteger( pCellList ) );
        charger.setDaysFromOpenToFirst( pImporter.getNextInteger( pCellList ) );
        charger.setRegion( pImporter.getNextString( pCellList ) );
        charger.setSort( pImporter.getNextString( pCellList ) );
        charger.setLatitude( pImporter.getNextString( pCellList ) );
        charger.setLongitude( pImporter.getNextString( pCellList ) );
        charger.setVisits( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2013( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2014( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2015( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2016( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2017( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2018( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2019( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2020( pImporter.getNextInteger( pCellList ) );
        charger.setVisits2021( pImporter.getNextInteger( pCellList ) );

        return charger;
    }

}
