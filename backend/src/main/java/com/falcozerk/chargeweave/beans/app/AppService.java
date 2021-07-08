package com.falcozerk.chargeweave.beans.app;

import com.falcozerk.chargeweave.beans.charger.ChargerRepository;
import com.falcozerk.chargeweave.beans.charger.ChargerService;
import com.falcozerk.chargeweave.beans.common.CwService;
import com.falcozerk.chargeweave.beans.user.UserRepository;
import com.falcozerk.chargeweave.beans.user.UserService;
import com.falcozerk.chargeweave.beans.visit.VisitRepository;
import com.falcozerk.chargeweave.beans.visit.VisitService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AppService extends CwService {
    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChargerRepository chargerRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    ChargerService chargerService;
    @Autowired
    UserService userService;
    @Autowired
    VisitService visitService;

    public static String SPREADSHEET_SPEC = "/SuperchargersVisited.xlsx";
    public static int COMPETITORS_TAB_POS = 4;
    public static int SUPERCHARGERS_TAB_POS = 2;

    public String importData() {
        try {
            Importer importer = new Importer();
            InputStream in = Importer.class.getResourceAsStream(SPREADSHEET_SPEC);
            Workbook workbook = new XSSFWorkbook(in);

            try {
//                userService.importFrom(importer, workbook, COMPETITORS_TAB_POS );
//                chargerService.importFrom(importer, workbook, SUPERCHARGERS_TAB_POS );
                visitService.importFrom(importer, workbook, SUPERCHARGERS_TAB_POS );
            }
            finally{
                workbook.close();
                in.close();
            }
        } catch( Exception e ) {
            e.printStackTrace();
            return "Could not connect.";
        }

        return "Chargers imported";
    }

    public String clearData() {
        try {
            visitRepository.deleteAll();
            userRepository.deleteAll();
            chargerRepository.deleteAll();
        } catch( Exception e ) {
            e.printStackTrace();
            return "Could not connect.";
        }

        return "Data deleted";
    }
}
