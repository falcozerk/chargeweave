package com.falcozerk.chargeweave.beans.visit;

import com.falcozerk.chargeweave.beans.app.Importer;
import com.falcozerk.chargeweave.beans.charger.Charger;
import com.falcozerk.chargeweave.beans.charger.ChargerRepository;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.user.User;
import com.falcozerk.chargeweave.beans.user.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class VisitService extends CwService {
    String FIRST_VISIT_HEADER_NAME = "JSergeant";

    @Autowired
    VisitRepository visitRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ChargerRepository chargerRepo;

    private static final Logger logger = LoggerFactory.getLogger(VisitService.class);

    public void importFrom(Importer pImporter, Workbook pWorkbook, int pTabId) {
        pImporter.init( pWorkbook, pTabId );
        ArrayList<Cell> cellList = new ArrayList<>();

        while( pImporter.importRow( cellList ) ) {
            ArrayList<Visit> aVisitList = importVisits( pImporter, cellList );
            visitRepo.saveAll( aVisitList );
        }
    }

    public ArrayList<Visit> importVisits( Importer pImporter, ArrayList<Cell> pCellList ) {
        ArrayList<Visit> aVisitList = new ArrayList<>();
        ArrayList<String> aHeaderList = pImporter.getHeaderList();
        int aPos = -1;
        while( !aHeaderList.get( ++aPos ).equals( FIRST_VISIT_HEADER_NAME ) );

        pImporter.setCellPos( -1 );
        Long aChargerId = pImporter.getNextLong( pCellList );
        pImporter.setCellPos( aPos );

        while ( pImporter.getCellPos() < pCellList.size() - 2 ) {
            String aHandle = aHeaderList.get(pImporter.getCellPos());
            if (StringUtils.isEmpty(aHandle) || StringUtils.startsWithIgnoreCase(aHandle, "zz"))
            {
                pImporter.setCellPos( pImporter.getCellPos() + 1 );
                continue;
            }

            Instant aVisitDate = pImporter.getNextDate(pCellList);
            if ( aVisitDate == null )
            {
                Cell aCell = pCellList.get(pImporter.getCellPos() );
                String aCellText = aCell.getStringCellValue();
                if ( StringUtils.isEmpty( aCellText ) ) continue;
            }

            Visit aVisit = createFrom(aChargerId, aHandle, aVisitDate);
            if ( aVisit != null ) aVisitList.add( aVisit );
        }

        return aVisitList;
    }

    public Visit createFrom( Long pChargerId, String pHandle, Instant pVisitDate ) {
        Optional<Charger> aCharger = chargerRepo.findBySid( pChargerId );
        Optional<User> aUser = userRepo.findByUsername( pHandle );
        if ( !aCharger.isPresent() || !aUser.isPresent()) return null;

        Visit visit = new Visit();
        visit.setCharger( aCharger.get() );
        visit.setUser( aUser.get() );
        visit.setVisitDate( pVisitDate );

        return visit;
    }

}
